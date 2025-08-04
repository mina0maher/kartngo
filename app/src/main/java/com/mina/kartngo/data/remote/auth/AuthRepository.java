package com.mina.kartngo.data.remote.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mina.kartngo.data.remote.entites.session.AssignStoreAndBringPropertiesRequest;
import com.mina.kartngo.data.remote.entites.session.AssignStoreAndBringPropertiesResponse;
import com.mina.kartngo.data.remote.entites.session.LoginResponse;
import com.mina.kartngo.data.util.Result;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final AuthApi authApi;
    private final Context context;
    public AuthRepository(AuthApi authApi, Context context) {
        this.authApi = authApi;
        this.context = context;
    }

    public LiveData<Result<LoginResponse>> login(String username, String password) {
        MutableLiveData<Result<LoginResponse>> result = new MutableLiveData<>();

        authApi.login(username, password, "test-pc", "2026", "false", "WEB_LINUX", "Normal")
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            String sessionId = null;
                            Headers headers = response.headers();
                            Log.d("API_LOG","THIS IS ALL HEADERS"+response.headers().toString());
                            for (String header : headers.values("Set-Cookie")) {
                                Log.d("API_LOG", "Cookie Header: " + header);

                                if (header.contains("JSESSIONID")) {
                                    String[] cookies = header.split(";");
                                    for (String cookie : cookies) {
                                        if (cookie.trim().startsWith("JSESSIONID=")) {
                                            sessionId = cookie.trim().substring("JSESSIONID=".length());
                                            break;
                                        }
                                    }
                                }
                            }
                            SessionManager sessionManager = new SessionManager(context);
                            if (sessionId!=null){
                                sessionManager.saveSessionId(sessionId);
                            }
                            sessionManager.saveToken(response.body().getToken());

                            Log.d("API_LOG","hi from repo this is token:"+response.body().getToken()+"and this is sessionID : "+sessionId);
                            result.setValue(new Result.Success<>(response.body()));
                        } else {
                            result.setValue(new Result.Error("Login failed"));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        result.setValue(new Result.Error(t.getMessage()));
                    }
                });

        return result;
    }

    public LiveData<Result<AssignStoreAndBringPropertiesResponse>> assignStoreAndBringProperties(String storeId) {
        MutableLiveData<Result<AssignStoreAndBringPropertiesResponse>> result = new MutableLiveData<>();

        authApi.assignStoreAndBringProperties(
                        new AssignStoreAndBringPropertiesRequest(
                                "sa.com.doit.cart.service.request.store.AssignStoreAndBringPropertiesRequest",
                                storeId
                        )
                )
                .enqueue(new Callback<AssignStoreAndBringPropertiesResponse>() {
                    @Override
                    public void onResponse(Call<AssignStoreAndBringPropertiesResponse> call, Response<AssignStoreAndBringPropertiesResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            SharedPreferences prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
                            prefs.edit()
                                    .putString("currency",
                                            response.
                                                    body().
                                                    getObject()
                                                    .getStore()
                                                    .getCurrencyCode()
                                    ).apply();
                            result.setValue(new Result.Success<>(response.body()));
                        } else {
                            result.setValue(new Result.Error(response.toString()));
                        }
                    }

                    @Override
                    public void onFailure(Call<AssignStoreAndBringPropertiesResponse> call, Throwable t) {
                        result.setValue(new Result.Error(t.getMessage()));
                    }
                });

        return result;
    }

}


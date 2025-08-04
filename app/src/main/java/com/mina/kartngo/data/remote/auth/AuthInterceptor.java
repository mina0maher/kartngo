package com.mina.kartngo.data.remote.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final SessionManager sessionManager;

    public AuthInterceptor(Context context) {
        sessionManager = new SessionManager(context);
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = sessionManager.getToken();
        String sessionId = sessionManager.getSessionId();

        Request originalRequest = chain.request();

        Request.Builder builder = originalRequest.newBuilder()
                .header("Content-Type", "application/json");
        if (token != null) {
            builder.header("Authorization","Bearer "+  token);
        }
        if (sessionId != null) {
            builder.header("Cookie", "JSESSIONID=" + sessionId);
        }
        Log.d("API_LOG", "Token: " + token + ", SessionId: " + sessionId);

        Request request = builder.build();
        return chain.proceed(request);
    }
}


package com.mina.kartngo.data.remote;

import android.content.Context;

import com.mina.kartngo.data.remote.auth.AuthApi;
import com.mina.kartngo.data.remote.auth.AuthInterceptor;
import com.mina.kartngo.data.remote.products.ProductsApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new LoggingInterceptor())
                    .addInterceptor(new AuthInterceptor(context))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://dev1.appxcart.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
    public static AuthApi getAuthApi(Context context) {
        return getClient(context).create(AuthApi.class);
    }

    public static ProductsApi getProductsApi(Context context) {
        return getClient(context).create(ProductsApi.class);
    }
}

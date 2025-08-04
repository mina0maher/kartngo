package com.mina.kartngo.data.remote;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();

        // Log Request Info
        Log.d("API_LOG", "⬆️ Sending request to: " + request.url());
        Log.d("API_LOG", "Method: " + request.method());

        // Log Headers
        Headers headers = request.headers();
        for (int i = 0; i < headers.size(); i++) {
            Log.d("API_LOG", headers.name(i) + ": " + headers.value(i));
        }

        // Log Body
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            String requestBody = buffer.readUtf8();
            Log.d("API_LOG", "Body: " + requestBody);
        }

        Response response = chain.proceed(request);

        // Log Response Info
        Log.d("API_LOG", "⬇️ Response Code: " + response.code());

        return response;
    }
}

package com.mina.kartngo.data.remote;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

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
        if (request.body() != null) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);
            String requestBody = buffer.readUtf8();
            Log.d("API_LOG", "Body: " + requestBody);
        } else {
            Log.d("API_LOG", "Body: null");
        }

        Response response = chain.proceed(request);

        // Log Response Info
        Log.d("API_LOG", "⬇️ Response Code: " + response.code());
        ResponseBody responseBody = response.body();
        if (responseBody != null) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.getBuffer();
            String responseBodyString = buffer.clone().readUtf8();
            Log.d("API_LOG", "Response Body: " + responseBodyString);

            // Return new response with the same body
            MediaType contentType = responseBody.contentType();
            ResponseBody newResponseBody = ResponseBody.create(responseBodyString, contentType);
            return response.newBuilder().body(newResponseBody).build();
        }
        return response;
    }
}

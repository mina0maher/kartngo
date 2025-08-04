package com.mina.kartngo.data.remote.auth;

import com.mina.kartngo.data.remote.entites.auth.AssignStoreAndBringPropertiesRequest;
import com.mina.kartngo.data.remote.entites.auth.AssignStoreAndBringPropertiesResponse;
import com.mina.kartngo.data.remote.entites.auth.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthApi {
    @FormUrlEncoded
    @POST("plugins/appx_offline_support_plugin/authenticate")
    Call<LoginResponse> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("source") String source,
            @Field("appVersion") String appVersion,
            @Field("kiosk") String kiosk,
            @Field("platform") String platform,
            @Field("appMode") String appMode
    );

    @POST("plugins/appx_offline_support_plugin/service/v1/AssignStoreAndBringProperties")
    Call<AssignStoreAndBringPropertiesResponse> assignStoreAndBringProperties(
            @Body AssignStoreAndBringPropertiesRequest request
    );

}

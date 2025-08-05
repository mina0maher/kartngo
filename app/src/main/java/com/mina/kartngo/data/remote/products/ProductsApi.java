package com.mina.kartngo.data.remote.products;

import com.mina.kartngo.data.remote.products.pojo.GetAllProductsRequest;
import com.mina.kartngo.data.remote.products.pojo.ImageRequest;
import com.mina.kartngo.data.remote.products.pojo.ImageResponse;
import com.mina.kartngo.data.remote.products.pojo.ProductsResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProductsApi {
    @POST("plugins/appx_offline_support_plugin/service/v1/GetAllProducts")
    Call<ProductsResponse> getAllProducts(
            @Body GetAllProductsRequest request
    );

    @POST("/plugins/appx_offline_support_plugin/service/v1/GetResource")
    Call<ImageResponse> getResource(
            @Body ImageRequest request
    );
}

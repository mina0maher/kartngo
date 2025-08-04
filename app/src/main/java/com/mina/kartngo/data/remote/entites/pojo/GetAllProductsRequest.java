package com.mina.kartngo.data.remote.entites.pojo;

import com.google.gson.annotations.SerializedName;

public class GetAllProductsRequest {
    @SerializedName("class")
    private String clazz = "sa.com.doit.cart.service.request.GetAllProductsRequest";

    @SerializedName("size")
    private int size;

    @SerializedName("start")
    private int start;

    public GetAllProductsRequest(int size, int start) {
        this.size = size;
        this.start = start;
    }

    // Getters & Setters

}

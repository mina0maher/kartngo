package com.mina.kartngo.data.remote.products.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsResponse {
    @SerializedName("list")
    private List<DetailedProduct> list;

    public List<DetailedProduct> getList() {
        return list;
    }

    public void setList(List<DetailedProduct> list) {
        this.list = list;
    }
}

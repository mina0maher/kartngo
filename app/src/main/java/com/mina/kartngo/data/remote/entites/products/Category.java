package com.mina.kartngo.data.remote.entites.products;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("categoryID")
    private String categoryID;

    @SerializedName("category")
    private String category;

    @SerializedName("description")
    private String description;

    @SerializedName("uid")
    private String uid;

    @SerializedName("store")
    private Store store;

    @SerializedName("avatar")
    private String avatar;

    // Getters and setters ...
}


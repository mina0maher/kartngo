package com.mina.kartngo.data.remote.products.pojo;

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

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategory() {
        return category;
    }

    public String getAvatar() {
        return avatar;
    }

    public Store getStore() {
        return store;
    }

    public String getUid() {
        return uid;
    }

    public String getDescription() {
        return description;
    }
}


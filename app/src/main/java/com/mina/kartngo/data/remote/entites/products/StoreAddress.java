package com.mina.kartngo.data.remote.entites.products;

import com.google.gson.annotations.SerializedName;

public class StoreAddress {

    @SerializedName("addressID")
    private String addressID;

    @SerializedName("postalCode")
    private String postalCode;

    @SerializedName("add1")
    private String add1;

    @SerializedName("add2")
    private String add2;

    @SerializedName("addressType")
    private String addressType;

    @SerializedName("username")
    private String username;

    @SerializedName("street")
    private String street;

    @SerializedName("addionalStreet")
    private String addionalStreet;

    @SerializedName("buildingNumber")
    private String buildingNumber;

    @SerializedName("addressAddionalNumber")
    private String addressAddionalNumber;

    @SerializedName("district")
    private String district;

    @SerializedName("area")
    private Area area;

    // Getters and setters ...
}


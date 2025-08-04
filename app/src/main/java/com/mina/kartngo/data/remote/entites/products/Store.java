package com.mina.kartngo.data.remote.entites.products;

import com.google.gson.annotations.SerializedName;

public class Store {

    @SerializedName("isPublic")
    private boolean isPublic;

    @SerializedName("isPhysical")
    private boolean isPhysical;

    @SerializedName("isOnline")
    private boolean isOnline;

    @SerializedName("storeId")
    private String storeId;

    @SerializedName("vatId")
    private String vatId;

    @SerializedName("name")
    private String name;

    @SerializedName("displayName")
    private String displayName;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("background")
    private String background;

    @SerializedName("majorColor")
    private String majorColor;

    @SerializedName("minorColor")
    private String minorColor;

    @SerializedName("active")
    private boolean active;

    @SerializedName("userName")
    private String userName;

    @SerializedName("defaultVat")
    private double defaultVat;

    @SerializedName("timeZoneName")
    private String timeZoneName;

    @SerializedName("storeAddress")
    private StoreAddress storeAddress;

    @SerializedName("description")
    private String description;

    @SerializedName("path")
    private String path;

    @SerializedName("exist")
    private boolean exist;

    @SerializedName("timeZoneUTCOffset")
    private String timeZoneUTCOffset;

    @SerializedName("currencyCode")
    private String currencyCode;

    @SerializedName("lockPass")
    private String lockPass;

    @SerializedName("partyInfo")
    private PartyInfo partyInfo;

    // Getters and setters ...
}


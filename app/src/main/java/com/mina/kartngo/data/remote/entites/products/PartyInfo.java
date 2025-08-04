package com.mina.kartngo.data.remote.entites.products;

import com.google.gson.annotations.SerializedName;

public class PartyInfo {

    @SerializedName("id")
    private String id;

    @SerializedName("partyFormalId")
    private String partyFormalId;

    @SerializedName("vatId")
    private String vatId;

    @SerializedName("registrationName")
    private String registrationName;

    @SerializedName("partyIdType")
    private String partyIdType;

    @SerializedName("partyType")
    private String partyType;

    @SerializedName("partyAddress")
    private PartyAddress partyAddress;

    @SerializedName("username")
    private String username;

    @SerializedName("businessCategory")
    private String businessCategory;

    // Getters and setters ...
}

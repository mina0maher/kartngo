package com.mina.kartngo.data.remote.entites.products;

import com.google.gson.annotations.SerializedName;

public class DetailedProduct {

    @SerializedName("productID")
    private String productID;

    @SerializedName("productName")
    private String productName;

    @SerializedName("description")
    private String description;

    @SerializedName("tag")
    private String tag;

    @SerializedName("type")
    private String type;

    @SerializedName("creationDateLong")
    private long creationDateLong;

    @SerializedName("lastUpdated")
    private long lastUpdated;

    @SerializedName("standardUnitPrice")
    private double standardUnitPrice;

    @SerializedName("VAT")
    private double VAT;

    @SerializedName("cost")
    private double cost;

    @SerializedName("rating")
    private double rating;

    @SerializedName("weight")
    private double weight;

    @SerializedName("defaultVAT")
    private boolean defaultVAT;

    @SerializedName("SKU")
    private String SKU;

    @SerializedName("UPC")
    private String UPC;

    @SerializedName("expirationNoticePeriodInDays")
    private int expirationNoticePeriodInDays;

    @SerializedName("minimum")
    private int minimum;

    @SerializedName("initialQuantity")
    private int initialQuantity;

    @SerializedName("category")
    private Category category;

    @SerializedName("productDefault")
    private boolean productDefault;

    @SerializedName("children")
    private int children;

    @SerializedName("customizable")
    private boolean customizable;

    @SerializedName("startPrice")
    private double startPrice;

    @SerializedName("store")
    private Store store;

    @SerializedName("primeProperty")
    private int primeProperty;

    @SerializedName("online")
    private boolean online;

    @SerializedName("hidden")
    private boolean hidden;

    @SerializedName("availableQuantity")
    private int availableQuantity;

    // Getters and setters ...

}


package com.mina.kartngo.data.remote.products.pojo;

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

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("availableQuantity")
    private int availableQuantity;

    // Getters and setters ...

    public String getAvatar(){
        return avatar;
    }
    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public String getType() {
        return type;
    }

    public long getCreationDateLong() {
        return creationDateLong;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public double getStandardUnitPrice() {
        return standardUnitPrice;
    }

    public double getVAT() {
        return VAT;
    }

    public double getCost() {
        return cost;
    }

    public double getRating() {
        return rating;
    }

    public double getWeight() {
        return weight;
    }

    public boolean isDefaultVAT() {
        return defaultVAT;
    }

    public String getSKU() {
        return SKU;
    }

    public String getUPC() {
        return UPC;
    }

    public int getExpirationNoticePeriodInDays() {
        return expirationNoticePeriodInDays;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getInitialQuantity() {
        return initialQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isProductDefault() {
        return productDefault;
    }

    public int getChildren() {
        return children;
    }

    public boolean isCustomizable() {
        return customizable;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public Store getStore() {
        return store;
    }

    public int getPrimeProperty() {
        return primeProperty;
    }

    public boolean isOnline() {
        return online;
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}


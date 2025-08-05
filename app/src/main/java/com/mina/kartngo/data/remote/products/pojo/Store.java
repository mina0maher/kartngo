package com.mina.kartngo.data.remote.products.pojo;

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

    public boolean isPublic() {
        return isPublic;
    }

    public boolean isPhysical() {
        return isPhysical;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getVatId() {
        return vatId;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getBackground() {
        return background;
    }

    public String getMajorColor() {
        return majorColor;
    }

    public String getMinorColor() {
        return minorColor;
    }

    public boolean isActive() {
        return active;
    }

    public String getUserName() {
        return userName;
    }

    public double getDefaultVat() {
        return defaultVat;
    }

    public String getTimeZoneName() {
        return timeZoneName;
    }

    public StoreAddress getStoreAddress() {
        return storeAddress;
    }

    public String getDescription() {
        return description;
    }

    public String getPath() {
        return path;
    }

    public boolean isExist() {
        return exist;
    }

    public String getTimeZoneUTCOffset() {
        return timeZoneUTCOffset;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getLockPass() {
        return lockPass;
    }

    public PartyInfo getPartyInfo() {
        return partyInfo;
    }
}


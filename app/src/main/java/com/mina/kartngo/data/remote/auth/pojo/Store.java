package com.mina.kartngo.data.remote.auth.pojo;

public class Store {
    private boolean isPublic;
    private boolean isPhysical;
    private boolean isOnline;
    private String storeId;
    private String vatId;
    private String name;
    private String displayName;
    private String avatar;
    private String background;
    private String majorColor;
    private String minorColor;
    private boolean active;
    private String userName;
    private double defaultVat;
    private String timeZoneName;
    private StoreAddress storeAddress;
    private String description;
    private String path;
    private boolean exist;
    private String timeZoneUTCOffset;
    private String currencyCode;
    private String lockPass;
    private PartyInfo partyInfo;

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


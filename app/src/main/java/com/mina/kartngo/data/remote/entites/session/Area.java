package com.mina.kartngo.data.remote.entites.session;

public class Area {
    private String id;
    private String displayName;
    private double latitude;
    private double longitude;
    private String areaPath;
    private int population;

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAreaPath() {
        return areaPath;
    }

    public int getPopulation() {
        return population;
    }
}

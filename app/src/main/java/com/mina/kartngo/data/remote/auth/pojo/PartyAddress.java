package com.mina.kartngo.data.remote.auth.pojo;

public class PartyAddress {
    private String addressID;
    private String postalCode;
    private String add1;
    private String add2;
    private String addressType;
    private String username;
    private String street;
    private String addionalStreet;
    private String buildingNumber;
    private String addressAddionalNumber;
    private String district;
    private Area area;

    // Getters

    public String getAddressID() {
        return addressID;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getAdd1() {
        return add1;
    }

    public String getAdd2() {
        return add2;
    }

    public String getAddressType() {
        return addressType;
    }

    public String getUsername() {
        return username;
    }

    public String getStreet() {
        return street;
    }

    public String getAddionalStreet() {
        return addionalStreet;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public String getAddressAddionalNumber() {
        return addressAddionalNumber;
    }

    public String getDistrict() {
        return district;
    }

    public Area getArea() {
        return area;
    }
}

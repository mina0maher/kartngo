package com.mina.kartngo.data.remote.entites.auth;

public class PartyInfo {
    private String id;
    private String partyFormalId;
    private String vatId;
    private String registrationName;
    private String partyIdType;
    private String partyType;
    private PartyAddress partyAddress;
    private String username;
    private String businessCategory;

    // Getters

    public String getId() {
        return id;
    }

    public String getPartyFormalId() {
        return partyFormalId;
    }

    public String getVatId() {
        return vatId;
    }

    public String getRegistrationName() {
        return registrationName;
    }

    public String getPartyIdType() {
        return partyIdType;
    }

    public String getPartyType() {
        return partyType;
    }

    public PartyAddress getPartyAddress() {
        return partyAddress;
    }

    public String getUsername() {
        return username;
    }

    public String getBusinessCategory() {
        return businessCategory;
    }
}


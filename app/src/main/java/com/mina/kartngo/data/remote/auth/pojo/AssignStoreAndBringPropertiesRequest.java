package com.mina.kartngo.data.remote.auth.pojo;

import com.google.gson.annotations.SerializedName;

public class AssignStoreAndBringPropertiesRequest {
    private String storeId;
    @SerializedName("class")
    private String className = "sa.com.doit.cart.service.request.store.AssignStoreAndBringPropertiesRequest";

    public AssignStoreAndBringPropertiesRequest( String storeId) {
        this.storeId = storeId;
    }

    public String getClassName() {
        return className;
    }



    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}


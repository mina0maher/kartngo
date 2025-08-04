package com.mina.kartngo.data.remote.auth.pojo;

import com.google.gson.annotations.SerializedName;

public class AssignStoreAndBringPropertiesRequest {
    private String storeId;
    @SerializedName("class")
    private String className;

    public AssignStoreAndBringPropertiesRequest(String className, String storeId) {
        this.className = className;
        this.storeId = storeId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}


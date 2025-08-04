package com.mina.kartngo.data.remote.auth.pojo;

import com.google.gson.annotations.SerializedName;

public class AssignStoreAndBringPropertiesResponse {
    @SerializedName("Object")
    private StoreChanged object;

    // Getter
    public StoreChanged getObject() { return object; }
}


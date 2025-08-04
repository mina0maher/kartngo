package com.mina.kartngo.data.remote.entites.session;

import com.google.gson.annotations.SerializedName;

public class AssignStoreAndBringPropertiesResponse {
    @SerializedName("Object")
    private StoreChanged object;

    // Getter
    public StoreChanged getObject() { return object; }
}


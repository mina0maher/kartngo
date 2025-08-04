package com.mina.kartngo.data.remote.auth.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StoreChanged {
    private Store store;
    @SerializedName("permissionList")
    @Expose
    private Object permissionList;
    private Map<String, String> properties;

    // Getters
    public Store getStore() { return store; }
    public List<String> getPermissionList() {
        List<String> result = new ArrayList<>();

        if (permissionList instanceof List) {
            result.addAll((List<String>) permissionList);

        } else if (permissionList instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) permissionList;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                result.add(entry.getValue().toString());
            }
        }

        return result;
    }

    public Map<String, String> getProperties() { return properties; }
}


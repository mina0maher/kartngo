package com.mina.kartngo.data.local.resources;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "resources")
public class ResourceEntity {
    @PrimaryKey
    @NotNull
    private String resourceId;

    public ResourceEntity(@NonNull String resourceId, String base64Data) {
        this.resourceId = resourceId;
        this.base64Data = base64Data;
    }

    public String getBase64Data() {
        return base64Data;
    }

    public void setBase64Data(String base64Data) {
        this.base64Data = base64Data;
    }

    @NonNull
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(@NonNull String resourceId) {
        this.resourceId = resourceId;
    }

    private String base64Data;
}

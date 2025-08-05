package com.mina.kartngo.data.local.resources;

import android.content.Context;

import com.mina.kartngo.data.local.AppDatabase;
import com.mina.kartngo.data.local.products.ProductDao;

public class ResourceRepository {
    private final ResourceDao resourceDao;

    public ResourceRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        resourceDao = db.resourceDao();
    }

    public interface ResourceCallback {
        void onLoaded(String base64Data);
        void onNotFound();
    }

    public void getResourceByIdAsync(String resourceId, ResourceCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            ResourceEntity cached = resourceDao.getResourceById(resourceId);
            if (cached != null) {
                callback.onLoaded(cached.getBase64Data());
            } else {
                callback.onNotFound();
            }
        });
    }

    public void insertResourceAsync(String resourceId, String base64Data) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            resourceDao.insertResource(new ResourceEntity(resourceId, base64Data));
        });
    }
}


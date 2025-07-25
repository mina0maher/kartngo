package com.mina.kartngo.data;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.mina.kartngo.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductRepository implements ProductDataSource {

    private final ProductDao productDao;
    private final ExecutorService executorService;

    public ProductRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        productDao = db.productDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public LiveData<List<Product>> getAllProducts() {
        LiveData<List<ProductEntity>> entityLiveData = productDao.getAll();
        return Transformations.map(entityLiveData, entities -> {
            List<Product> models = new ArrayList<>();
            if (entities != null) {
                for (ProductEntity entity : entities) {
                    models.add(mapEntityToModel(entity));
                }
            }
            return models;
        });
    }

    @Override
    public void insertProduct(Product product) {
        executorService.execute(() -> productDao.insert(mapModelToEntity(product)));
    }

    @Override
    public void insertProducts(List<Product> products) {
        executorService.execute(() -> {
            List<ProductEntity> entities = new ArrayList<>();
            for (Product model : products) {
                entities.add(mapModelToEntity(model));
            }
            productDao.insertAll(entities);
        });
    }

    private Product mapEntityToModel(ProductEntity entity) {
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getImageUrl(),
                entity.getCategory(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }

    private ProductEntity mapModelToEntity(Product model) {
        ProductEntity entity = new ProductEntity(
                model.getName(),
                model.getImageUrl(),
                model.getCategory(),
                model.getPrice(),
                model.getCurrency()
        );
        entity.setId(model.getId());
        return entity;
    }
}
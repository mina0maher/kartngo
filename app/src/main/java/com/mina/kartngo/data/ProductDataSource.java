package com.mina.kartngo.data;

import androidx.lifecycle.LiveData;

import com.mina.kartngo.models.Product;

import java.util.List;

public interface ProductDataSource {

    LiveData<List<Product>> getAllProducts();

    void insertProduct(Product product);

    void insertProducts(List<Product> products);
}
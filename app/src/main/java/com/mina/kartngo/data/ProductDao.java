package com.mina.kartngo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM products")
    LiveData<List<ProductEntity>> getAll();

    @Insert
    void insert(ProductEntity product);

    @Insert
    void insertAll(List<ProductEntity> products);
}
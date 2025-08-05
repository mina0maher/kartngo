package com.mina.kartngo.data.local.resources;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ResourceDao {

    @Query("SELECT * FROM resources WHERE resourceId = :resourceId LIMIT 1")
    ResourceEntity getResourceById(String resourceId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResource(ResourceEntity resource);
}


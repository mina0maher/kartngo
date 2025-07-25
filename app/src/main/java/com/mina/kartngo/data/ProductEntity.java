package com.mina.kartngo.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    private String imageUrl;

    private String category;

    private double price;

    private String currency;

    public ProductEntity(String name, String imageUrl, String category, double price, String currency) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
        this.currency = currency;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

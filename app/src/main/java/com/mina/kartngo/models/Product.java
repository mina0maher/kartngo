package com.mina.kartngo.models;

public class Product {

    private int id;
    private String name;
    private String imageUrl;
    private String category;
    private double price;
    private String currency;

    public Product(int id, String name, String imageUrl, String category, double price, String currency) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
        this.currency = currency;
    }

    public Product(String name, String imageUrl, String category, double price, String currency) {
        this(0, name, imageUrl, category, price, currency);
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

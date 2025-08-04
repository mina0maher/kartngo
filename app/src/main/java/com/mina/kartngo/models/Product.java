package com.mina.kartngo.models;

public class Product {

    private int id;
    private String name;
    private String image;
    private String category;
    private double price;
    private String details;

    public Product(int id, String name, String image, String category, double price, String details) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
        this.price = price;
        this.details = details;
    }




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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }
}

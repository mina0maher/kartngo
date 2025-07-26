package com.mina.kartngo.screens;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mina.kartngo.data.ProductRepository;
import com.mina.kartngo.models.OrderItem;
import com.mina.kartngo.models.Product;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainViewModel extends ViewModel {

    private final ProductRepository repository;

    // LiveData for all products fetched from repository
    private final LiveData<List<Product>> productsLiveData;

    // Backing field for current order items
    private final MutableLiveData<List<OrderItem>> currentOrderLiveData = new MutableLiveData<>(new ArrayList<>());

    public MainViewModel(Application application) {
        repository = new ProductRepository(application);
        productsLiveData = repository.getAllProducts();
    }

    // Expose products LiveData
    public LiveData<List<Product>> getProductsLiveData() {
        return productsLiveData;
    }

    // Expose current order LiveData
    public LiveData<List<OrderItem>> getCurrentOrderLiveData() {
        return currentOrderLiveData;
    }

    // Add product to current order or increase quantity if already present
    public void addProductToOrder(Product product) {
        List<OrderItem> currentOrder = currentOrderLiveData.getValue();
        if (currentOrder == null) {
            currentOrder = new ArrayList<>();
        }

        int index = findOrderItemIndex(currentOrder, product);
        if (index >= 0) {
            OrderItem existingItem = currentOrder.get(index);
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            currentOrder.add(new OrderItem(product, 1));
        }
        currentOrderLiveData.setValue(currentOrder);
    }

    // Remove product from current order or decrease quantity
    public void removeProductFromOrder(Product product) {
        List<OrderItem> currentOrder = currentOrderLiveData.getValue();
        if (currentOrder == null) return;

        int index = findOrderItemIndex(currentOrder, product);
        if (index >= 0) {
            OrderItem existingItem = currentOrder.get(index);
            int qty = existingItem.getQuantity();
            if (qty > 1) {
                existingItem.setQuantity(qty - 1);
            } else {
                currentOrder.remove(index);
            }
            currentOrderLiveData.setValue(currentOrder);
        }
    }

    // Clear the current order
    public void clearOrder() {
        currentOrderLiveData.setValue(new ArrayList<>());
    }

    // Helper to find index of OrderItem by product id
    private int findOrderItemIndex(List<OrderItem> orderItems, Product product) {
        for (int i = 0; i < orderItems.size(); i++) {
            if (Objects.equals(orderItems.get(i).getProduct().getId(), product.getId())) {
                return i;
            }
        }
        return -1;
    }


}

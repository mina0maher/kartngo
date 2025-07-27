package com.mina.kartngo.screens;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.mina.kartngo.data.ProductRepository;

import com.mina.kartngo.models.OrderItem;
import com.mina.kartngo.models.Product;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class MainViewModel extends AndroidViewModel {
    private final ProductRepository repository;
    private final LiveData<List<Product>> productsLiveData;
    private final MutableLiveData<List<OrderItem>> currentOrderLiveData = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<String>> categoriesLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> selectedCategoryLiveData = new MutableLiveData<>(null);
    private final MediatorLiveData<List<Product>> filteredProductsLiveData = new MediatorLiveData<>();
    private final MutableLiveData<String> searchQueryLiveData = new MutableLiveData<>("");

    public void setSearchQuery(String query) {
        searchQueryLiveData.setValue(query);
    }

    public void clearSearchQuery() {
        searchQueryLiveData.setValue("");
    }

    public LiveData<String> getSearchQueryLiveData() {
        return searchQueryLiveData;
    }
    public MainViewModel(Application application) {
        super(application);
        repository = new ProductRepository(application);
        productsLiveData = repository.getAllProducts();


        productsLiveData.observeForever(products -> {
            if (products != null) {
                Set<String> uniqueCategories = new LinkedHashSet<>();
                for (Product product : products) {
                    if (product.getCategory() != null && !product.getCategory().isEmpty()) {
                        uniqueCategories.add(product.getCategory());
                    }
                }
                categoriesLiveData.setValue(new ArrayList<>(uniqueCategories));
            }
        });

             filteredProductsLiveData.addSource(productsLiveData, products -> filterProducts());
        filteredProductsLiveData.addSource(selectedCategoryLiveData, category -> filterProducts());
        filteredProductsLiveData.addSource(searchQueryLiveData, s -> filterProducts());

    }

    private void filterProducts() {
        List<Product> allProducts = productsLiveData.getValue();
        String selectedCategory = selectedCategoryLiveData.getValue();
        String query = searchQueryLiveData.getValue();

        if (allProducts == null) return;

        List<Product> filtered = new ArrayList<>();
        for (Product product : allProducts) {
            boolean matchesCategory = selectedCategory == null || selectedCategory.isEmpty()
                    || selectedCategory.equals(product.getCategory());
            boolean matchesQuery = query == null || query.isEmpty()
                    || product.getName().toLowerCase().contains(query.toLowerCase());

            if (matchesCategory && matchesQuery) {
                filtered.add(product);
            }
        }
        filteredProductsLiveData.setValue(filtered);
    }



    public LiveData<List<Product>> getFilteredProductsLiveData() {
        return filteredProductsLiveData;
    }

    public void setSelectedCategory(String category) {
        selectedCategoryLiveData.setValue(category);
    }

    public LiveData<List<String>> getCategoriesLiveData() {
        return categoriesLiveData;
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return productsLiveData;
    }

    public LiveData<List<OrderItem>> getCurrentOrderLiveData() {
        return currentOrderLiveData;
    }

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

    public void clearOrder() {
        currentOrderLiveData.setValue(new ArrayList<>());
    }


    private int findOrderItemIndex(List<OrderItem> orderItems, Product product) {
        for (int i = 0; i < orderItems.size(); i++) {
            if (Objects.equals(orderItems.get(i).getProduct().getId(), product.getId())) {
                return i;
            }
        }
        return -1;
    }
}

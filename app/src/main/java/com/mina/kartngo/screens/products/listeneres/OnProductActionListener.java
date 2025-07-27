package com.mina.kartngo.screens.products.listeneres;

import com.mina.kartngo.models.Product;

public interface OnProductActionListener {
    void onIncreaseClicked(Product product);
     void onDecreaseClicked(Product product);
     void onProductClicked(Product product);
}

package com.mina.kartngo.screens.products.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.kartngo.R;
import com.mina.kartngo.models.OrderItem;
import com.mina.kartngo.models.Product;
import com.mina.kartngo.screens.products.listeneres.OnProductActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private List<OrderItem> currentOrderList = new ArrayList<>();
    private OnProductActionListener listener;

    public ProductAdapter(OnProductActionListener listener) {
        this.listener = listener;
    }

    public void setProducts(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.currentOrderList = orderItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.textTitle.setText(product.getName());
        holder.textPrice.setText(product.getPrice() + " " + product.getCurrency());

        int quantity = 0;
        for (OrderItem item : currentOrderList) {
            if (item.getProduct().getId() == product.getId()) {
                quantity = item.getQuantity();
                break;
            }
        }
        holder.textCount.setText(String.valueOf(quantity));

        // Load images (optional with Glide or your method)
        Glide.with(holder.imgProduct.getContext())
                .load(product.getImageUrl())
                .into(holder.imgProduct);

        holder.buttonPlus.setOnClickListener(v -> listener.onIncreaseClicked(product));
        holder.buttonMinus.setOnClickListener(v -> listener.onDecreaseClicked(product));
        holder.layout.setOnClickListener(v -> listener.onProductClicked(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle, textPrice, textCount;
        ImageView imgProduct;
        TextView buttonPlus, buttonMinus;
        LinearLayout layout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
            textCount = itemView.findViewById(R.id.textCounter);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            buttonPlus = itemView.findViewById(R.id.buttonPlus);
            buttonMinus = itemView.findViewById(R.id.buttonMinus);
        }
    }
}

package com.mina.kartngo.screens.products.adapters;

import static com.mina.kartngo.screens.utils.Helpers.decodeBase64ToBitmap;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mina.kartngo.R;
import com.mina.kartngo.models.OrderItem;
import com.mina.kartngo.models.Product;
import com.mina.kartngo.screens.products.listeneres.OnProductActionListener;
import com.mina.kartngo.screens.utils.GenericDiffCallback;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {

    private List<OrderItem> currentOrderList = new ArrayList<>();
    private final OnProductActionListener listener;
    private final String currency;

    public ProductAdapter(OnProductActionListener listener, String currency) {
        super(new GenericDiffCallback<>(
                (oldItem, newItem) -> oldItem.getId() == newItem.getId(), // areItemsSame
                Product::equals // areContentsSame
        ));
        this.listener = listener;
        this.currency = currency;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.currentOrderList = orderItems != null ? orderItems : new ArrayList<>();
        notifyDataSetChanged(); // optional: remove if you diff order list separately
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = getItem(position);

        holder.textTitle.setText(product.getName());
        holder.textPrice.setText(product.getPrice() + " " + currency);

        int quantity = 0;
        for (OrderItem item : currentOrderList) {
            if (item.getProduct().getId() == product.getId()) {
                quantity = item.getQuantity();
                break;
            }
        }
        holder.textCount.setText(String.valueOf(quantity));

        Bitmap bitmap = decodeBase64ToBitmap(product.getImage());
        if (bitmap != null) {
            Glide.with(holder.imgProduct.getContext())
                    .load(bitmap)
                    .into(holder.imgProduct);
        } else {
            holder.imgProduct.setImageResource(R.drawable.logo);
        }

        holder.buttonPlus.setOnClickListener(v -> listener.onIncreaseClicked(product));
        holder.buttonMinus.setOnClickListener(v -> listener.onDecreaseClicked(product));
        holder.layout.setOnClickListener(v -> listener.onProductClicked(product));
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

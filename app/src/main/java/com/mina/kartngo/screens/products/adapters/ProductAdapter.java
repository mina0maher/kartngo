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
import java.util.Objects;

public class ProductAdapter extends ListAdapter<Product, ProductAdapter.ProductViewHolder> {

    private final List<Product> fullProductList = new ArrayList<>();
    private List<OrderItem> currentOrderList = new ArrayList<>();
    private final OnProductActionListener listener;
    private final String currency;

    public ProductAdapter(OnProductActionListener listener, String currency) {
        super(new GenericDiffCallback<>(
                (oldItem, newItem) -> Objects.equals(oldItem.getId(), newItem.getId()),
                (oldItem, newItem) ->
                        Objects.equals(oldItem.getId(), newItem.getId()) &&
                                Objects.equals(oldItem.getImage(), newItem.getImage())
        ));
        this.listener = listener;
        this.currency = currency;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.currentOrderList = orderItems != null ? orderItems : new ArrayList<>();
        // نعمل submitList لنفس الليست علشان يشتغل DiffUtil
        submitList(new ArrayList<>(getCurrentList()));
    }

    public void setProducts(List<Product> newProducts) {
        fullProductList.clear();
        if (newProducts != null) {
            fullProductList.addAll(newProducts);
        }
        submitList(new ArrayList<>(fullProductList));
    }

    public void addItems(List<Product> newProducts) {
        if (newProducts != null && !newProducts.isEmpty()) {
            fullProductList.addAll(newProducts);
            submitList(new ArrayList<>(fullProductList));
        }
    }

    public void clearItems() {
        fullProductList.clear();
        submitList(new ArrayList<>());
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

        int quantity = getQuantityForProduct(product);
        holder.textCount.setText(String.valueOf(quantity));

        Bitmap productBitmap = decodeBase64ToBitmap(product.getImage());
        if (productBitmap != null) {
            Glide.with(holder.imgProduct.getContext())
                    .load(productBitmap)
                    .into(holder.imgProduct);
        } else {
            holder.imgProduct.setImageResource(R.drawable.ic_product);
        }

        Bitmap storeBitmap = decodeBase64ToBitmap(product.getStoreImage());
        if (storeBitmap != null) {
            Glide.with(holder.imgStore.getContext())
                    .load(storeBitmap)
                    .into(holder.imgStore);
        } else {
            holder.imgProduct.setImageResource(R.drawable.ic_store);
        }

        holder.buttonPlus.setOnClickListener(v -> {
            listener.onIncreaseClicked(product);
            notifyItemChanged(holder.getAdapterPosition());
        });

        holder.buttonMinus.setOnClickListener(v -> {
            listener.onDecreaseClicked(product);
            notifyItemChanged(holder.getAdapterPosition());
        });
        holder.layout.setOnClickListener(v -> listener.onProductClicked(product));
    }

    private int getQuantityForProduct(Product product) {
        for (OrderItem item : currentOrderList) {
            if (Objects.equals(item.getProduct().getId(), product.getId())) {
                return item.getQuantity();
            }
        }
        return 0;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle, textPrice, textCount;
        ImageView imgProduct,imgStore;
        TextView buttonPlus, buttonMinus;
        LinearLayout layout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            textTitle = itemView.findViewById(R.id.textTitle);
            textPrice = itemView.findViewById(R.id.textPrice);
            textCount = itemView.findViewById(R.id.textCounter);
            imgStore = itemView.findViewById(R.id.imgStore);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            buttonPlus = itemView.findViewById(R.id.buttonPlus);
            buttonMinus = itemView.findViewById(R.id.buttonMinus);
        }
    }
}

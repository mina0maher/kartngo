package com.mina.kartngo.screens.products.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.kartngo.R;
import com.mina.kartngo.screens.products.listeneres.OnCategoryClickListener;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private List<String> categories;
    private String selectedCategory = null;
    private OnCategoryClickListener listener;



    public CategoriesAdapter(List<String> categories, OnCategoryClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String category = categories.get(position);
        holder.categoryName.setText(category);

        boolean isSelected = category.equals(selectedCategory);


        int textColor = ContextCompat.getColor(holder.itemView.getContext(),
                isSelected ? R.color.teal_mist : R.color.navy_ink);
        holder.categoryName.setTextColor(textColor);

        holder.checkIcon.setVisibility(isSelected ? View.VISIBLE : View.GONE);
        holder.container.setSelected(isSelected);
        holder.card.setOnClickListener(v -> {
            if (category.equals(selectedCategory)) {
                selectedCategory = null; // unselect if selected again
                notifyDataSetChanged();
                listener.onCategoryClick(null);
            } else {
                selectedCategory = category;
                notifyDataSetChanged();
                listener.onCategoryClick(category);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView checkIcon;
        CardView card;
        LinearLayout container;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            card = itemView.findViewById(R.id.category_card);
            categoryName = itemView.findViewById(R.id.category_name);
            checkIcon = itemView.findViewById(R.id.check_icon);
            container = itemView.findViewById(R.id.category_container);
        }
    }
}


package com.mina.kartngo.screens.products;

import static com.mina.kartngo.screens.utils.ToastUtils.showToast;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.kartngo.R;
import com.mina.kartngo.models.Product;
import com.mina.kartngo.models.OrderItem;
import com.mina.kartngo.screens.MainViewModel;
import com.mina.kartngo.screens.products.adapters.CategoriesAdapter;
import com.mina.kartngo.screens.products.adapters.ProductAdapter;
import com.mina.kartngo.screens.products.listeneres.OnCategoryClickListener;
import com.mina.kartngo.screens.products.listeneres.OnProductActionListener;

public class ProductsFragment extends Fragment {

    private MainViewModel viewModel;
    private ProductAdapter productAdapter;
    private CategoriesAdapter categoriesAdapter;
    private RecyclerView recyclerProducts, recyclerCategories;
    private View filterContainer;
    private EditText etSearch;
    private ImageView ivFilter, ivClearFilter;
    private CardView btnViewCart;
    private TextView tvTotalPrice,tvCurrency;

    public ProductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filterContainer = view.findViewById(R.id.filterContainer);
        etSearch = view.findViewById(R.id.etSearch);
        ivFilter = view.findViewById(R.id.ivFilter);
        ivClearFilter = view.findViewById(R.id.ivClearFilter);
        btnViewCart = view.findViewById(R.id.btnViewCart);
        tvTotalPrice = view.findViewById(R.id.tvTotalPrice);
        tvCurrency = view.findViewById(R.id.tvCurrency);
        recyclerProducts = view.findViewById(R.id.rvProducts);
        recyclerCategories = view.findViewById(R.id.rvCategories);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        setupCategoryRecycler();
        setupProductRecycler();
        observeData();
        setListeners();
    }

    private void setListeners(){
        ivFilter.setOnClickListener(v -> {
            if (filterContainer.getVisibility() == View.GONE) {
                filterContainer.setVisibility(View.VISIBLE);
                etSearch.requestFocus();
            } else {
                filterContainer.setVisibility(View.GONE);
                viewModel.clearSearchQuery();
                etSearch.setText("");
            }
        });

        ivClearFilter.setOnClickListener(v -> {
            etSearch.setText("");
            viewModel.clearSearchQuery();
            filterContainer.setVisibility(View.GONE);
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.setSearchQuery(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

            btnViewCart.setOnClickListener(v -> {

                NavHostFragment.findNavController(ProductsFragment.this)
                        .navigate(R.id.action_productsFragment_to_orderFragment);
            });


    }
    private void setupCategoryRecycler() {
        recyclerCategories.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        categoriesAdapter = new CategoriesAdapter(
                new java.util.ArrayList<>(),
                new OnCategoryClickListener() {
                    @Override
                    public void onCategoryClick(String category) {
                        viewModel.setSelectedCategory(category);
                    }
                });

        recyclerCategories.setAdapter(categoriesAdapter);
    }

    private void setupProductRecycler() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerProducts.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(new OnProductActionListener() {
            @Override
            public void onIncreaseClicked(Product product) {
                viewModel.addProductToOrder(product);
            }

            @Override
            public void onDecreaseClicked(Product product) {
                viewModel.removeProductFromOrder(product);
            }

            @Override
            public void onProductClicked(Product product) {
                String message = "🍔 " + product.getName() + "\n"
                        + "Price: " + product.getPrice() + " "+ product.getCurrency();

                showToast(requireContext(), message);
            }
        });

        recyclerProducts.setAdapter(productAdapter);
    }

    private void observeData() {
        viewModel.getCategoriesLiveData().observe(getViewLifecycleOwner(), categories -> {
            categoriesAdapter = new CategoriesAdapter(categories, category -> {
                viewModel.setSelectedCategory(category);
            });
            recyclerCategories.setAdapter(categoriesAdapter);
        });

        viewModel.getFilteredProductsLiveData().observe(getViewLifecycleOwner(), products -> {
            productAdapter.setProducts(products);
        });

        viewModel.getCurrentOrderLiveData().observe(getViewLifecycleOwner(), orderItems -> {
            productAdapter.setOrderItems(orderItems);
        });


        viewModel.getCurrentOrderLiveData().observe(getViewLifecycleOwner(), orderItems -> {
            productAdapter.setOrderItems(orderItems);

            if (orderItems != null && !orderItems.isEmpty()) {
                btnViewCart.setVisibility(View.VISIBLE);

                double total = 0;
                for (OrderItem item : orderItems) {
                    total += item.getQuantity() * item.getProduct().getPrice();
                }

                String text = String.format("%.2f", total) ;
                tvCurrency.setText(orderItems.get(0).getProduct().getCurrency());
                tvTotalPrice.setText(text);

            } else {
                btnViewCart.setVisibility(View.GONE);
            }
        });

    }
}

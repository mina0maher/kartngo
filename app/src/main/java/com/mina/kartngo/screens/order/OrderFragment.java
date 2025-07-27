package com.mina.kartngo.screens.order;

import static com.mina.kartngo.screens.utils.ToastUtils.showToast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mina.kartngo.R;
import com.mina.kartngo.models.OrderItem;
import com.mina.kartngo.models.Product;
import com.mina.kartngo.screens.MainViewModel;
import com.mina.kartngo.screens.products.adapters.ProductAdapter;
import com.mina.kartngo.screens.products.listeneres.OnProductActionListener;

import java.util.ArrayList;
import java.util.List;
public class OrderFragment extends Fragment {

    private MainViewModel viewModel;
    private ProductAdapter productAdapter;
    private RecyclerView rvOrderItems;
    private TextView tvTotalPrice, tvCurrency;
    private ImageView btnBack;
    private CardView btnConfirm;

    public OrderFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order, container, false); // layout فيه نفس الـ RecyclerView إللي مستخدمه
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        rvOrderItems = view.findViewById(R.id.rvOrderItems);
        tvTotalPrice = view.findViewById(R.id.tvCartTotal);
        tvCurrency = view.findViewById(R.id.tvCartCurrency);
        btnBack = view.findViewById(R.id.btnBack);
        btnConfirm = view.findViewById(R.id.btnConfirmOrder);

        setupRecyclerView();
        observeOrderItems();

        btnBack.setOnClickListener(v -> {
            NavHostFragment.findNavController(OrderFragment.this).navigateUp();
        });


        btnConfirm.setOnClickListener(v -> {
            viewModel.clearOrder();
            showToast(requireContext(), "تم تأكيد الطلب ✅");
            NavHostFragment.findNavController(OrderFragment.this).navigateUp();
        });
    }

    private void setupRecyclerView() {
        rvOrderItems.setLayoutManager(new LinearLayoutManager(requireContext()));

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

        rvOrderItems.setAdapter(productAdapter);
    }

    private void observeOrderItems() {
        viewModel.getCurrentOrderLiveData().observe(getViewLifecycleOwner(), orderItems -> {

            if (orderItems == null || orderItems.isEmpty()) {
                NavHostFragment.findNavController(OrderFragment.this).navigateUp();
                return;
            }

            List<Product> orderedProducts = new ArrayList<>();
            for (OrderItem item : orderItems) {
                orderedProducts.add(item.getProduct());
            }

            productAdapter.setProducts(orderedProducts);
            productAdapter.setOrderItems(orderItems);

            updateTotalPrice(orderItems);
        });
    }

    private void updateTotalPrice(List<OrderItem> orderItems) {
        double total = 0;
        String currency = "AED";

        if (orderItems != null) {
            for (OrderItem item : orderItems) {
                total += item.getProduct().getPrice() * item.getQuantity();
                currency = item.getProduct().getCurrency();
            }
        }

        tvTotalPrice.setText(String.format("%.2f", total));
        tvCurrency.setText(currency);
    }
}

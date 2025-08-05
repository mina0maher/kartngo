package com.mina.kartngo.screens.details;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mina.kartngo.R;
import com.mina.kartngo.data.local.resources.ResourceRepository;
import com.mina.kartngo.data.remote.ApiClient;
import com.mina.kartngo.data.remote.products.ProductsApi;
import com.mina.kartngo.data.remote.products.ProductsRepository;
import com.mina.kartngo.models.Product;
import com.mina.kartngo.screens.MainViewModel;
import com.mina.kartngo.screens.utils.ViewModelFactory;

public class DetailsFragment extends Fragment {

    private static final String ARG_PRODUCT_ID = "productId";

    private MainViewModel viewModel;
    private ImageView ivImage, btnBack;
    private TextView tvName, tvPrice, tvCategory, tvDescription;

    private String productId;
    private Product product;
    private  SharedPreferences prefs;

    public DetailsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefs = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);

        ivImage = view.findViewById(R.id.imgProduct);
        btnBack = view.findViewById(R.id.btnBack);
        tvName = view.findViewById(R.id.tvName);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvCategory = view.findViewById(R.id.tvCategory);
        tvDescription = view.findViewById(R.id.tvDescription);

        setupViewModel();

        if (getArguments() != null) {
            productId = getArguments().getString(ARG_PRODUCT_ID);
            product = viewModel.getProductById(productId);

            if (product != null) {
                Glide.with(requireContext())
                        .load(product.getImage())
                        .into(ivImage);

                tvName.setText(product.getName());
                tvPrice.setText(product.getPrice() + " " + prefs.getString("currency", null));
                tvCategory.setText(product.getCategory());
                tvDescription.setText(product.getDetails());
            }
        }

        btnBack.setOnClickListener(v ->
                NavHostFragment.findNavController(DetailsFragment.this).navigateUp()
        );
    }
    private void setupViewModel() {
        ProductsApi productsApi = ApiClient.getProductsApi(requireContext());
        ResourceRepository resourceRepository = new ResourceRepository(requireContext());
        ProductsRepository productsRepository = new ProductsRepository(productsApi,resourceRepository);
        viewModel = new ViewModelProvider(
                requireActivity(),
                new ViewModelFactory(requireActivity().getApplication(),productsRepository )
        ).get(MainViewModel.class);
    }
}

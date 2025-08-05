package com.mina.kartngo.data.remote.products;

import static com.mina.kartngo.data.util.Helpers.parseLocalizedString;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mina.kartngo.data.local.resources.ResourceRepository;
import com.mina.kartngo.data.remote.products.pojo.DetailedProduct;
import com.mina.kartngo.data.remote.products.pojo.GetAllProductsRequest;
import com.mina.kartngo.data.remote.products.pojo.Hashx;
import com.mina.kartngo.data.remote.products.pojo.ImageRequest;
import com.mina.kartngo.data.remote.products.pojo.ImageResponse;
import com.mina.kartngo.data.remote.products.pojo.ProductsResponse;
import com.mina.kartngo.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsRepository {

    private final ProductsApi productsApi;
    private final ResourceRepository resourceRepository;
    public ProductsRepository(ProductsApi productsApi, ResourceRepository resourceRepository) {
        this.productsApi = productsApi;
        this.resourceRepository = resourceRepository;
    }
    
//    public void fetchImage(String imageId){
//        productsApi.getResource(new ImageRequest(imageId)).enqueue(new Callback<ImageResponse>() {
//            @Override
//            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
//                String base64Data = response.body().getResource().getHashx().getBase64Data();
//            }
//
//            @Override
//            public void onFailure(Call<ImageResponse> call, Throwable t) {
//
//            }
//        });
//    }
    public void fetchProducts(String language, int size, int start, MutableLiveData<List<Product>> liveData) {
        GetAllProductsRequest request = new GetAllProductsRequest(size, start);
        productsApi.getAllProducts(request).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DetailedProduct> rawList = response.body().getList();
                    List<Product> mappedList = mapToUiProducts(language, rawList);
                    liveData.postValue(mappedList); // Step 1: Show products without image

                    for (int i = 0; i < rawList.size(); i++) {
                        int index = i;
                        String imageId = rawList.get(index).getAvatar();

                        if (imageId != null && !imageId.isEmpty()) {
                            resourceRepository.getResourceByIdAsync(imageId, new ResourceRepository.ResourceCallback() {
                                @Override
                                public void onLoaded(String base64Data) {
                                    mappedList.get(index).setImage(base64Data);
                                    liveData.postValue(new ArrayList<>(mappedList)); // Update with cached image
                                }

                                @Override
                                public void onNotFound() {
                                    productsApi.getResource(new ImageRequest(imageId)).enqueue(new Callback<ImageResponse>() {
                                        @Override
                                        public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                                            if (response.isSuccessful() && response.body() != null) {
                                                Hashx hashx = response.body().getResource().getHashx();
                                                if (hashx != null && hashx.getBase64Data() != null) {
                                                    String base64 = hashx.getBase64Data();
                                                    mappedList.get(index).setImage(base64);
                                                    liveData.postValue(new ArrayList<>(mappedList)); // Update with new image

                                                    resourceRepository.insertResourceAsync(imageId, base64); // Cache it
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ImageResponse> call, Throwable t) {
                                            // Ignore image fetch failure
                                        }
                                    });
                                }
                            });
                        }
                    }

                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }
    private List<Product> mapToUiProducts(String language, List<DetailedProduct> detailedList) {
        List<Product> uiList = new ArrayList<>();

        for (DetailedProduct dp : detailedList) {
            Product product = new Product(
                    dp.getProductID(),
                    parseLocalizedString(dp.getProductName(),language),
                    dp.getAvatar(),
                    (dp.getCategory() != null && dp.getCategory().getCategory() != null)
                            ? parseLocalizedString(dp.getCategory().getCategory(), language)
                            : parseLocalizedString("[en=Uncategorized][ar=غير مصنف]",language),
                    dp.getStandardUnitPrice(),
                    dp.getDescription() != null ? parseLocalizedString(dp.getDescription(),language) : ""
            );
            uiList.add(product);
        }

        return uiList;
    }

}

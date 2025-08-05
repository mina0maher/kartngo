package com.mina.kartngo.data.remote.entites;

import static com.mina.kartngo.data.util.Helpers.parseLocalizedString;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.mina.kartngo.data.remote.entites.pojo.DetailedProduct;
import com.mina.kartngo.data.remote.entites.pojo.GetAllProductsRequest;
import com.mina.kartngo.data.remote.entites.pojo.Hashx;
import com.mina.kartngo.data.remote.entites.pojo.ImageRequest;
import com.mina.kartngo.data.remote.entites.pojo.ImageResponse;
import com.mina.kartngo.data.remote.entites.pojo.ProductsResponse;
import com.mina.kartngo.models.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {

    private final ProductsApi productsApi;
    public ProductRepository(ProductsApi productsApi) {
        this.productsApi = productsApi;
    }
    
    public void fetchImage(String imageId){
        productsApi.getResource(new ImageRequest(imageId)).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                String base64Data = response.body().getResource().getHashx().getBase64Data();
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });
    }
    public void fetchProducts(String language, int size, int start, MutableLiveData<List<Product>> liveData) {
        GetAllProductsRequest request = new GetAllProductsRequest(size, start);
        productsApi.getAllProducts(request).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DetailedProduct> rawList = response.body().getList();
                    List<Product> mappedList = mapToUiProducts(language, rawList);
                    liveData.postValue(mappedList); // Step 1: Show products without image

                    // Step 2: fetch images asynchronously
                    for (int i = 0; i < rawList.size(); i++) {
                        int index = i;
                        String imageId = rawList.get(index).getAvatar();

                        if (imageId != null && !imageId.isEmpty()) {
                            productsApi.getResource(new ImageRequest(imageId)).enqueue(new Callback<ImageResponse>() {
                                @Override
                                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        Hashx hashx = response.body().getResource().getHashx();
                                        if (hashx != null && hashx.getBase64Data() != null) {
                                            Log.d("hi from repository","    "+hashx.getBase64Data());
                                            String base64 = hashx.getBase64Data();
                                            mappedList.get(index).setImage(base64);
                                            liveData.postValue(new ArrayList<>(mappedList)); // re-post updated list
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ImageResponse> call, Throwable t) {
                                    // Ignore individual image failure
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
                    dp.getProductName(),
                    dp.getAvatar(),
                    dp.getCategory() != null ? parseLocalizedString(dp.getCategory().getCategory(),language): "[en=Uncategorized][ar=غير مصنف]",
                    dp.getStandardUnitPrice(),
                    dp.getDescription() != null ? dp.getDescription() : ""
            );
            uiList.add(product);
        }

        return uiList;
    }

}

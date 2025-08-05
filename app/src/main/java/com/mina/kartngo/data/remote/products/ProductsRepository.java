package com.mina.kartngo.data.remote.products;

import static com.mina.kartngo.data.util.Helpers.parseLocalizedString;

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

    public void fetchProducts(String language, int size, int start, MutableLiveData<List<Product>> liveData) {
        GetAllProductsRequest request = new GetAllProductsRequest(size, start);

        productsApi.getAllProducts(request).enqueue(new Callback<ProductsResponse>() {
            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DetailedProduct> rawList = response.body().getList();
                    List<Product> mappedList = mapToUiProducts(language, rawList);

                    liveData.postValue(mappedList); // Step 1: Show products without images

                    for (int i = 0; i < rawList.size(); i++) {
                        int index = i;

                        String productImageId = rawList.get(index).getAvatar();
                        String storeImageId = rawList.get(index).getStore().getAvatar();

                        // ✅ تحميل صورة المنتج
                        fetchAndAttachImage(productImageId, index, mappedList, liveData, true);

                        // ✅ تحميل صورة المتجر
                        fetchAndAttachImage(storeImageId, index, mappedList, liveData, false);
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

    private void fetchAndAttachImage(String imageId, int index, List<Product> mappedList, MutableLiveData<List<Product>> liveData, boolean isProductImage) {
        if (imageId == null || imageId.isEmpty()) return;

        resourceRepository.getResourceByIdAsync(imageId, new ResourceRepository.ResourceCallback() {
            @Override
            public void onLoaded(String base64Data) {
                if (isProductImage) {
                    mappedList.get(index).setImage(base64Data);
                } else {
                    mappedList.get(index).setStoreImage(base64Data);
                }
                liveData.postValue(new ArrayList<>(mappedList));
            }

            @Override
            public void onNotFound() {
                fetchImageFromApi(imageId, new ResourceCallback() {
                    @Override
                    public void onImageFetched(String base64) {
                        if (isProductImage) {
                            mappedList.get(index).setImage(base64);
                        } else {
                            mappedList.get(index).setStoreImage(base64);
                        }
                        liveData.postValue(new ArrayList<>(mappedList));
                    }
                });
            }
        });
    }

    private void fetchAndCacheOnly(String imageId) {
        if (imageId == null || imageId.isEmpty()) return;

        resourceRepository.getResourceByIdAsync(imageId, new ResourceRepository.ResourceCallback() {
            @Override
            public void onLoaded(String base64Data) {
                // Already cached, nothing to do
            }

            @Override
            public void onNotFound() {
                fetchImageFromApi(imageId, null); // No UI update needed
            }
        });
    }

    private void fetchImageFromApi(String imageId, ResourceCallback callback) {
        productsApi.getResource(new ImageRequest(imageId)).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Hashx hashx = response.body().getResource().getHashx();
                    if (hashx != null && hashx.getBase64Data() != null) {
                        String base64 = hashx.getBase64Data();

                        // Cache image
                        resourceRepository.insertResourceAsync(imageId, base64);

                        if (callback != null) {
                            callback.onImageFetched(base64);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                Log.e("ProductsRepo", "Failed to fetch image: " + imageId, t);
            }
        });
    }

    private List<Product> mapToUiProducts(String language, List<DetailedProduct> detailedList) {
        List<Product> uiList = new ArrayList<>();

        for (DetailedProduct dp : detailedList) {
            Product product = new Product(
                    dp.getProductID(),
                    parseLocalizedString(dp.getProductName(), language),
                    dp.getAvatar(),
                    (dp.getCategory() != null && dp.getCategory().getCategory() != null)
                            ? parseLocalizedString(dp.getCategory().getCategory(), language)
                            : parseLocalizedString("[en=Uncategorized][ar=غير مصنف]", language),
                    dp.getStandardUnitPrice(),
                    dp.getDescription() != null ? parseLocalizedString(dp.getDescription(), language) : "",
                    dp.getStore().getAvatar()
            );
            uiList.add(product);
        }

        return uiList;
    }

    private interface ResourceCallback {
        void onImageFetched(String base64);
    }
}

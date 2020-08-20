package com.example.shoppingpoint.model;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.shoppingpoint.service.RestApiService;
import com.example.shoppingpoint.service.RetrofitInstance;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/*
 Interact via Retrofit and putting data set back to model using product and cart model classes.
 Repository for interacting to LiveData.
 */
public class ProductRepository {

    private List<Product> productList = new ArrayList<>();
    private List<CartItem> cartItemList = new ArrayList<>();
    private MutableLiveData<List<Product>> productsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<CartItem>> cartItemsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Object> cartItemMessage = new MutableLiveData<>();
    private String api_key = "ddd971ff0a-faf0-40e9-afbb-319f5d50856c";

    public ProductRepository() {
    }

    public MutableLiveData<List<Product>> getProductsMutableLiveData() {
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<List<Product>> call = apiService.getProducts(api_key);
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> mProducts = response.body();
                if (mProducts != null) {
                    productList = (ArrayList<Product>) mProducts;
                    productsMutableLiveData.setValue(productList);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
            }
        });
        return productsMutableLiveData;
    }

    public MutableLiveData<List<CartItem>> getCartItemsMutableLiveData() {
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<List<CartItem>> call = apiService.getCarts(api_key);
        call.enqueue(new Callback<List<CartItem>>() {
            @Override
            public void onResponse(Call<List<CartItem>> call, Response<List<CartItem>> response) {
                List<CartItem> mCartItems = response.body();
                if (mCartItems != null) {
                    cartItemList = (ArrayList<CartItem>) mCartItems;
                    cartItemsMutableLiveData.setValue(cartItemList);
                }
            }

            @Override
            public void onFailure(Call<List<CartItem>> call, Throwable t) {
            }
        });
        return cartItemsMutableLiveData;
    }

    public MutableLiveData<Object> sendCartItemMutableLiveData(int id) {
        try {
            RestApiService apiService = RetrofitInstance.getApiService();

            Call<Object> call = apiService.sendCartProduct(api_key, id);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    cartItemMessage.setValue(response.body());
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItemMessage;
    }

    public MutableLiveData<Object> deleteCartItemMutableLiveData(int id) {
        try {
            RestApiService apiService = RetrofitInstance.getApiService();

            Call<Object> call = apiService.deleteCartProduct(api_key, id);
            call.enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    cartItemMessage.setValue(response.body());
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItemMessage;
    }

}
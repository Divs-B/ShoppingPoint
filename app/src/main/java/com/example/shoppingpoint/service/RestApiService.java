package com.example.shoppingpoint.service;

import com.example.shoppingpoint.model.CartItem;
import com.example.shoppingpoint.model.Product;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RestApiService {

    @GET("cloths/products")
    Call<List<Product>> getProducts(@Header("X-API-KEY") String apiKey);

    @GET("cloths/cart")
    Call<List<CartItem>> getCarts(@Header("X-API-KEY") String apiKey);

    @Headers("Content-Type: application/json")
    @POST("cloths/cart")
    Call<Object> sendCartProduct(@Header("X-API-KEY") String apiKey, @Query("productId") int id);

    @Headers("Content-Type: application/json")
    @DELETE("cloths/cart")
    Call<Object> deleteCartProduct(@Header("X-API-KEY") String apiKey, @Query("id") int id);
}


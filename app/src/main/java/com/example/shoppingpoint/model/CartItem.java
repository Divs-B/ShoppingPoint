package com.example.shoppingpoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartItem {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("productId")
    @Expose
    private int productId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setproductId(int productId) {
        this.productId = productId;
    }
}

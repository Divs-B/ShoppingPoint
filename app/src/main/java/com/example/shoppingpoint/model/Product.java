package com.example.shoppingpoint.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/*
Model class for Product items
 */
public class Product {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("oldPrice")
    @Expose
    private String oldPrice;
    @SerializedName("stock")
    @Expose
    private int stock;

    public Product() {
    }

    public Product(int id, String name, String category, String price, String oldPrice, int stock) {
        super();
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.oldPrice = oldPrice;
        this.stock = stock;
    }


    private boolean iscartAdded = false;
    private boolean isWishListAdded = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean getIsCartAdded() {
        return iscartAdded;
    }

    public void setIscartAdded(boolean iscartAdded) {
        this.iscartAdded = iscartAdded;
    }

    public boolean getIsWishlistAdded() {
        return isWishListAdded;
    }

    public void setIsWishlistAdded(boolean isWishListAdded) {
        this.isWishListAdded = isWishListAdded;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("name", name).append("category", category).append("price", price).append("oldPrice", oldPrice).append("stock", stock).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(price).append(oldPrice).append(name).append(id).append(category).append(stock).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Product) == false) {
            return false;
        }
        Product rhs = ((Product) other);
        return new EqualsBuilder().append(price, rhs.price).append(oldPrice, rhs.oldPrice).append(name, rhs.name).append(id, rhs.id).append(category, rhs.category).append(stock, rhs.stock).isEquals();
    }
}


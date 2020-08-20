package com.example.shoppingpoint.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wishlist")
public class WishlistDBModel {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "productId")
    private int mProductId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "price")
    private String mPrice;

    public WishlistDBModel(int mProductId, String mName, String mPrice) {
        this.mProductId = mProductId;
        this.mName = mName;
        this.mPrice = mPrice;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int mProductId) {
        this.mProductId = mProductId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String mPrice) {
        this.mPrice = mPrice;
    }
}
package com.example.shoppingpoint.ui;

public interface OnItemClick {

    public void onClick(int id, String name, String price, boolean toRemoveItemFromCart, boolean toAddItemInWishList, boolean toRemoveItemFromWishListToCart);
}

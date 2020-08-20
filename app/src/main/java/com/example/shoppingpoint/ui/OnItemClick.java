package com.example.shoppingpoint.ui;

/*
Interface to implement callback when click to add or remove items on respective Adapters
 */
public interface OnItemClick {

    public void onClick(int id, String name, String price, boolean toRemoveItemFromCart, boolean toAddItemInWishList, boolean toRemoveItemFromWishListToCart);
}

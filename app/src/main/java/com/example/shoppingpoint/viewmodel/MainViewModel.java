package com.example.shoppingpoint.viewmodel;

import androidx.lifecycle.LiveData;

import java.util.List;

import androidx.lifecycle.ViewModel;

import com.example.shoppingpoint.model.CartItem;
import com.example.shoppingpoint.model.Product;
import com.example.shoppingpoint.model.ProductRepository;

/*
ViewModel class
 */
public class MainViewModel extends ViewModel {

    private ProductRepository productRepository;

    public MainViewModel() {
        super();
        productRepository = new ProductRepository();
    }

    public LiveData<List<Product>> getAllProducts() {
        return productRepository.getProductsMutableLiveData();
    }

    public LiveData<List<CartItem>> getAllCartItems() {
        return productRepository.getCartItemsMutableLiveData();
    }

    public LiveData<Object> sendCartItem(int id) {
        return productRepository.sendCartItemMutableLiveData(id);
    }

    public LiveData<Object> deleteCartItem(int id) {
        return productRepository.deleteCartItemMutableLiveData(id);
    }


}

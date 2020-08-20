package com.example.shoppingpoint.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.shoppingpoint.R;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingpoint.db.WishlistDB;
import com.example.shoppingpoint.db.WishlistDBModel;
import com.example.shoppingpoint.model.CartItem;
import com.example.shoppingpoint.model.Product;
import com.example.shoppingpoint.viewmodel.MainViewModel;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Divya Bhatt on 16.08.2020,
 * for Deloitte Code-Test , with given api : https://app.swaggerhub.com/apis/eeliseev/cloths/v1#
 *
 * I have kept Api-key for authorization under BuildConfig config in app/build gradle file
 * Git Repository for this project hence kept private so to not to expose or shared publically.
 *
 * This is the single activity that holds view for UI and top level function for handling MutableLiveData.
 * This has Product, Cart and Wishlist adapters objects and there respective inflation on Recycler View
 */
public class MainActivity extends AppCompatActivity implements OnItemClick {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout swipeRefresh;
    ActionBar actionBar;
    private MainViewModel mainViewModel;

    ProductAdapter mProductAdapter;
    CartItemAdapter mCartItemAdapter;
    WishListItemAdapter mWishListItemAdapter;

    private List<Product> mProductList;
    private String totalPriceString = "0.0";
    private MenuItem totalPriceMenu;
    private WishlistDB wishlist_database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializationViews();
        wishlist_database = WishlistDB.getDatabaseInstance(this);
        mainViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(MainViewModel.class);
        getProducts();
        getCartItemsOnly();
        // lambda expression
        swipeRefresh.setOnRefreshListener(() -> {
            getProducts();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        menu.getItem(1).setTitle(totalPriceString);
        totalPriceMenu = menu.findItem(R.id.cart_text);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().toString().equalsIgnoreCase(getResources().getString(R.string.cart))) {
            getCartItems();
        } else if (item.getTitle().toString().equalsIgnoreCase(getResources().getString(R.string.wishlist))) {
            getWishListItems();
        } else if (item.getTitle().toString().equalsIgnoreCase(getResources().getString(R.string.home))) {
            getProducts();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializationViews() {
        swipeRefresh = findViewById(R.id.swiperefresh);
        mRecyclerView = findViewById(R.id.recyclerView);
        actionBar = getSupportActionBar();
    }

    public void getProducts() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> productList) {
                swipeRefresh.setRefreshing(false);
                mProductList = productList;
                prepareRecyclerView_Products(productList);
            }
        });
    }

    public void getCartItemsOnly() {
        mainViewModel.getAllCartItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> cartItemList) {
                setTotalPrice(cartItemList);
            }
        });
    }

    public void getCartItems() {
        swipeRefresh.setRefreshing(true);
        mainViewModel.getAllCartItems().observe(this, new Observer<List<CartItem>>() {
            @Override
            public void onChanged(@Nullable List<CartItem> cartItemList) {
                swipeRefresh.setRefreshing(false);
                setTotalPrice(cartItemList);
                prepareRecyclerView_CartItems(cartItemList, mProductList);
            }
        });
    }

    public void sendCartItem(int id) {
        mainViewModel.sendCartItem(id).observe(this, new Observer<Object>() {
            @Override
            public void onChanged(@Nullable Object obj) {
                swipeRefresh.setRefreshing(false);
                getCartItemsOnly();
            }
        });
    }

    public void deleteCartItem(int id) {
        mainViewModel.deleteCartItem(id).observe(this, new Observer<Object>() {
            @Override
            public void onChanged(@Nullable Object obj) {
                swipeRefresh.setRefreshing(false);
                getCartItems();
            }
        });
    }

    @Override
    public void onClick(int id, String name, String price, boolean toRemoveItemFromCart, boolean toAddItemInWishList, boolean toRemoveItemFromWishListToCart) {
        if (!toAddItemInWishList) {
            if (!toRemoveItemFromCart) {
                //if(toRemoveItemFromWishListToCart)
                //wishlist_database.wishlistDao().deleteRecord(id);
                sendCartItem(id);
            } else {
                deleteCartItem(id);
            }
        } else {
            setWishlistToDB(id, name, price);
        }
    }

    private void prepareRecyclerView_Products(List<Product> productList) {
        mProductAdapter = new ProductAdapter(this, productList, this);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mProductAdapter);
        if (actionBar != null)
            actionBar.setTitle(getResources().getString(R.string.home));
        mProductAdapter.notifyDataSetChanged();
    }

    private void prepareRecyclerView_CartItems(List<CartItem> cartItemList, List<Product> productList) {
        mCartItemAdapter = new CartItemAdapter(cartItemList, productList, this);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mCartItemAdapter);
        if (actionBar != null)
            actionBar.setTitle(getResources().getString(R.string.cart));
        mCartItemAdapter.notifyDataSetChanged();
    }

    private void prepareRecyclerView_WishListItems(List<WishlistDBModel> wishList, List<Product> productList) {
        mWishListItemAdapter = new WishListItemAdapter(wishList, productList, this);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        }
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mWishListItemAdapter);
        if (actionBar != null)
            actionBar.setTitle(getResources().getString(R.string.wishlist));
        mWishListItemAdapter.notifyDataSetChanged();
    }

    private void setWishlistToDB(int id, String name, String price) {
        WishlistDBModel wishlistRec = new WishlistDBModel(id, name, price);
        wishlist_database.wishlistDao().insertWishlistData(wishlistRec);
    }

    private List<WishlistDBModel> getAllWishList() {
        return wishlist_database.wishlistDao().getAll();
    }

    public void getWishListItems() {
        swipeRefresh.setRefreshing(false);
        List<WishlistDBModel> list = getAllWishList();
        prepareRecyclerView_WishListItems(list, mProductList);
    }

    private void setTotalPrice(List<CartItem> cartItemList) {
        try {
            Double totalPrice = 0.0;
            if (mProductList != null && cartItemList != null) {
                for (int i = 0; i < cartItemList.size(); i++) {
                    int _id = cartItemList.get(i).getProductId();
                    for (int j = 0; j < mProductList.size(); j++) {
                        if (_id == mProductList.get(j).getId()) {
                            Double price = Double.parseDouble(mProductList.get(j).getPrice());
                            totalPrice += price;
                        }
                    }
                }
            }
            totalPriceString = "£" + totalPrice;
            totalPriceMenu.setTitle(totalPriceString);
        } catch (NumberFormatException ne) {
            System.out.println(ne.getMessage());
        }
    }

}
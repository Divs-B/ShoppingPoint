package com.example.shoppingpoint.model;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.shoppingpoint.viewmodel.MainViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

@RunWith(MockitoJUnitRunner.class)
public class ProductRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    Observer<List<Product>> productObserver;
    @Mock
    Observer<Product> productObs;

    private MainViewModel viewModel;
    private ProductRepository productRepo;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = new MainViewModel();
        productRepo = new ProductRepository();
    }

    @Test
    public void testProductDataFromServer() {

        //Assemble
        List<Product> productList = new ArrayList<>();

        //Act
        viewModel.getAllProducts().observeForever(productObserver);
        productRepo.getProductsMutableLiveData();

        //Assert
        assertNotNull(productRepo.getProductsMutableLiveData());
    }

    @After
    public void tearDown() throws Exception {

    }

}

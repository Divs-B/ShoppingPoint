package com.example.shoppingpoint.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import retrofit2.http.DELETE;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface WishlistDao {

    @Query("SELECT * FROM wishlist")
    List<WishlistDBModel> getAll();

    @Insert(onConflict = REPLACE)
    void insertWishlistData(WishlistDBModel mWishlistDBModel);

    @Insert
    void insertAllWishlistData(WishlistDBModel... mWishlistDBModelList);

    @Delete
    void delete(WishlistDBModel mWishlistDBModel);

    @Update
    void updateUser(WishlistDBModel mWishlistDBModel);

    @Query("SELECT * FROM wishlist WHERE uid = :uId")
    WishlistDBModel getUserById(int uId);


    @Query("SELECT * FROM wishlist WHERE uid IN (:wishlistdbmodelIds)")
    List<WishlistDBModel> loadAllByIds(int[] wishlistdbmodelIds);

}

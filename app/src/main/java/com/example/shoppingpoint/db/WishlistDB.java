package com.example.shoppingpoint.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/*
Database definition via Room api class for wishlist model
 */
@Database(entities = {WishlistDBModel.class}, version = 1, exportSchema = false)
public abstract class WishlistDB extends RoomDatabase {

    private static WishlistDB mInstance;
    private static final String DATABASE_NAME = "wishlist-database";

    public abstract WishlistDao wishlistDao();

    public synchronized static WishlistDB getDatabaseInstance(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(), WishlistDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }

    public static void destroyInstance() {
        mInstance = null;
    }

}

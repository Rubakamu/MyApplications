package com.example.primegen.cart.local_database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Cart.class}, version = 1, exportSchema = false)
public abstract class DatabaseController extends RoomDatabase {
    private static DatabaseController mData;

    public static DatabaseController getInstance(Context context) {
        if (mData == null) {
            mData = Room.databaseBuilder(context.getApplicationContext(), DatabaseController.class,
                    "TestDB")
                    .build();
        }
        return mData;
    }

    public abstract TestDAO getTestDAO();

}

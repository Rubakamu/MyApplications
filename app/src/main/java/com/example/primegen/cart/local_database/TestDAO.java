package com.example.primegen.cart.local_database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDAO {
    @Insert
    public void addToCart(Cart cart);

    @Query("SELECT * FROM MyCart")
    public List<Cart>getData();

    @Query("SELECT EXISTS (SELECT 1 FROM mycart WHERE testprofileID=:id)")
    public int isAddToCart(int id);

    @Query("select COUNT (*) from MyCart")
    int countCart();

    @Query("DELETE FROM MyCart WHERE testprofileID=:id ")
    int deleteItem(int id);

}

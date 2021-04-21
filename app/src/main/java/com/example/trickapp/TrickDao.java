package com.example.trickapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TrickDao {

    @Insert
    void insert(Trick trick);

    @Update
    void update(Trick trick);

    @Delete
    void delete(Trick trick);

    @Query("SELECT * FROM trick_table")
    LiveData<List<Trick>> getAllTricks();
}

package com.example.trickapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities =  {Trick.class}, version = 1)
public abstract class TrickDatabase extends RoomDatabase {
    private static TrickDatabase instance;

    public abstract TrickDao trickDao();

    public static synchronized TrickDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TrickDatabase.class, "trick_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;

    }
}

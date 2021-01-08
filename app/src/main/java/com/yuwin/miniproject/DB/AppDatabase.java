package com.yuwin.miniproject.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yuwin.miniproject.DB.Dao.FoodTypeDao;
import com.yuwin.miniproject.DB.Entity.FoodTypeEntity;

@Database(entities = {FoodTypeEntity.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FoodTypeDao foodTypeDao();

    private static AppDatabase INSTANCE = null;

    public static synchronized AppDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "appDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return INSTANCE;
    }



}

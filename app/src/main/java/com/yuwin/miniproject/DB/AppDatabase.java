package com.yuwin.miniproject.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yuwin.miniproject.DB.Dao.CardInfoDao;
import com.yuwin.miniproject.DB.Dao.FoodTypeDao;
import com.yuwin.miniproject.DB.Entity.CardEntity;
import com.yuwin.miniproject.DB.Entity.FoodTypeEntity;

@Database(entities = {FoodTypeEntity.class, CardEntity.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FoodTypeDao foodTypeDao();
    public abstract CardInfoDao cardInfoDao();

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

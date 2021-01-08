package com.yuwin.miniproject.DB.Dao;

import androidx.annotation.MainThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.yuwin.miniproject.DB.Entity.FoodTypeEntity;

import java.util.List;

@Dao
public interface FoodTypeDao {

    @Query("Select Count(*) from food_type")
    int count();

    @Query("Select * from food_type")
    LiveData<List<FoodTypeEntity>> getAllFoodTypes();

    @Insert
    void insertFoodType(FoodTypeEntity foodTypeEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFoodTypes(List<FoodTypeEntity> foodTypeEntities);

    @Update
    void updateFoodType(FoodTypeEntity foodTypeEntity);

}

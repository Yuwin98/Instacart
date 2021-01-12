package com.yuwin.miniproject.DB.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.yuwin.miniproject.DB.Entity.CardEntity;

import java.util.List;

@Dao
public interface CardInfoDao {

    @Query("Select Count(*) from user_card")
    int count();

    @Query("Select * from user_card")
    CardEntity getUserCards();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCardData(CardEntity cardData);

}

package com.yuwin.miniproject.DB.Entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "food_type")
public class FoodTypeEntity {

    public FoodTypeEntity(String foodTypeName, String foodTypeImageName) {
        this.foodTypeName = foodTypeName;
        this.foodTypeImageName = foodTypeImageName;
    }

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "ft_name")
    public String foodTypeName;

    @ColumnInfo(name = "ft_image")
    public String foodTypeImageName;


    public String getFoodTypeName() {
        return foodTypeName;
    }

    public String getFoodTypeImageName() {
        return foodTypeImageName;
    }
}

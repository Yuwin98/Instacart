package com.yuwin.miniproject.Models;

public class FoodTypeData {

    private String foodName;
    private String imageResourceName;

    public FoodTypeData(String foodName, String imageResourceName) {
        this.foodName = foodName;
        this.imageResourceName = imageResourceName;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getImageResourceName() {
        return imageResourceName;
    }

}

package com.yuwin.miniproject.Models;

import com.yuwin.miniproject.utility.OrderProgress;

public class OrderModel {

    private String imgUrl;
    private String mealName;
    private String totalPrice;
    private String stars;
    private String dateTime;
    private OrderProgress progress;

    public OrderModel(String imgUrl, String mealName, String totalPrice, String stars, String dateTime, OrderProgress progress) {
        this.imgUrl = imgUrl;
        this.mealName = mealName;
        this.totalPrice = totalPrice;
        this.stars = stars;
        this.progress = progress;
        this.dateTime = dateTime;
    }

    public String getDateTime() {
        return "At - " + dateTime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getMealName() {
        return mealName;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public String getStars() {
        return stars;
    }

    public String getProgress() {
        return "Status - " + progress.toString();
    }
}

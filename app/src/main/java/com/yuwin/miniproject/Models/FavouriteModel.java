package com.yuwin.miniproject.Models;



public class FavouriteModel {

    private String name;
    private String img;
    private String stars;
    private String hours;

    public FavouriteModel(String name, String img, String stars, String hours) {
        this.name = name;
        this.img = img;
        this.stars = stars;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getStars() {
        return stars;
    }

    public String getHours() {
        return hours;
    }
}

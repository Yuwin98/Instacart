package com.yuwin.miniproject.Models;

public class DiscoverRestaurant {

    private String imageID;
    private String restaurantName;
    private String restaurantDistance;


    public DiscoverRestaurant(String imageID, String restaurantName, String restaurantDistance) {
        this.imageID = imageID;
        this.restaurantName = restaurantName;
        this.restaurantDistance = restaurantDistance;
    }

    public String getImageID() {
        return imageID;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantDistance() {
        return restaurantDistance;
    }
}

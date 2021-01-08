package com.yuwin.miniproject.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class AvailableMeal implements Parcelable {

    private String ImageID;
    private String mealName;
    private String mealPrice;


    public AvailableMeal(String imageID, String mealName, String mealPrice) {
        ImageID = imageID;
        this.mealName = mealName;
        this.mealPrice = mealPrice;
    }

    protected AvailableMeal(Parcel in) {
        ImageID = in.readString();
        mealName = in.readString();
        mealPrice = in.readString();
    }

    public static final Creator<AvailableMeal> CREATOR = new Creator<AvailableMeal>() {
        @Override
        public AvailableMeal createFromParcel(Parcel in) {
            return new AvailableMeal(in);
        }

        @Override
        public AvailableMeal[] newArray(int size) {
            return new AvailableMeal[size];
        }
    };

    public String getImageID() {
        return ImageID;
    }

    public void setImageID(String imageID) {
        ImageID = imageID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealPrice() {
        return mealPrice + "$";
    }

    public float getMealOrders() {
        return Float.parseFloat(mealPrice) / 10;
    }

    public float getMealPriceAsNumber() {
        return Float.parseFloat(mealPrice);
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ImageID);
        parcel.writeString(mealName);
        parcel.writeString(mealPrice);
    }
}

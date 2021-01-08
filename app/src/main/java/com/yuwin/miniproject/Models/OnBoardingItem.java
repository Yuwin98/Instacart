package com.yuwin.miniproject.Models;

public class OnBoardingItem {

    private int onBoardingImage;
    private String onBoardingText;

    public OnBoardingItem(int onBoardingImage, String onBoardingText) {
        this.onBoardingImage = onBoardingImage;
        this.onBoardingText = onBoardingText;
    }

    public int getOnBoardingImage() {
        return onBoardingImage;
    }

    public String getOnBoardingText() {
        return onBoardingText;
    }
}

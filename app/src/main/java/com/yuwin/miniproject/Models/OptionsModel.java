package com.yuwin.miniproject.Models;

public class OptionsModel {

    private String img;
    private String name;
    private String price;
    private Boolean isSelected = false;

    public OptionsModel(String img, String name, String price) {
        this.img = img;
        this.name = name;
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public float getPriceAsFloat(){
        return Float.parseFloat(price);
    }
}

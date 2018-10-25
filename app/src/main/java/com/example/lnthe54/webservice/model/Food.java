package com.example.lnthe54.webservice.model;

/**
 * @author lnthe54 on 10/22/2018
 * @project WebService
 */
public class Food {
    private long id;
    private String foodName;
    private int foodPrice;
    private String foodImg;
    private String address;
    private String time;
    private String description;

    public Food(String foodName, int foodPrice, String foodImg, String time, String address, String description) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodImg = foodImg;
        this.time = time;
        this.address = address;
        this.description = description;
    }

    public Food(long id, String foodName, int foodPrice, String foodImg, String products, String description) {
        this.id = id;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodImg = foodImg;
        this.address = products;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodImg() {
        return foodImg;
    }

    public void setFoodImg(String foodImg) {
        this.foodImg = foodImg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

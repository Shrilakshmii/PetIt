package com.example.petit_forkt.model;

public class Cart {
    private String foodtitle,foodprice,foodquantity;

    public Cart() {

    }

    public Cart(String foodtitle, String foodprice, String foodquantity) {
        this.foodtitle = foodtitle;
        this.foodprice = foodprice;
        this.foodquantity = foodquantity;
    }

    public String getFoodtitle() {
        return foodtitle;
    }

    public void setFoodtitle(String foodtitle) {
        this.foodtitle = foodtitle;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }

    public String getFoodquantity() {
        return foodquantity;
    }

    public void setFoodquantity(String foodquantity) {
        this.foodquantity = foodquantity;
    }
}

package com.example.petit_forkt.model;

public class Final {
    private String TotalPrice;
    private String ProductName;
    private String ProductQuantity;
    private String TableNumber;

    public Final(String totalPrice, String productName, String productQuantity, String tableNumber) {
        TotalPrice = totalPrice;
        ProductName = productName;
        ProductQuantity = productQuantity;
        TableNumber = tableNumber;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getTableNumber() {
        return TableNumber;
    }

    public void setTableNumber(String tableNumber) {
        TableNumber = tableNumber;
    }
}


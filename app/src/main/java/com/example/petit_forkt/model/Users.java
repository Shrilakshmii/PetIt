package com.example.petit_forkt.model;

public class Users {

    private String Email;
    private String Password;
    private String Phone;
    private int userType;

    public Users()
    {
    }

    public Users(String Email, String Password, String Phone, int userType) {
        this.Email = Email;
        this.Password = Password;
        this.Phone = Phone;
        this.userType = userType;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}

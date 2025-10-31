package com.shop.controllers.models;

public class Profile {
    private int id;
    private String shopName;
    private String gstNumber;
    private String address;
    private String phoneNumber;
    private String logoPath;

    // Default constructor
    public Profile() {}

    // Constructor with parameters
    public Profile(String shopName, String gstNumber, String address, String phoneNumber, String logoPath) {
        this.shopName = shopName;
        this.gstNumber = gstNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.logoPath = logoPath;
    }

    // Constructor with id
    public Profile(int id, String shopName, String gstNumber, String address, String phoneNumber, String logoPath) {
        this.id = id;
        this.shopName = shopName;
        this.gstNumber = gstNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.logoPath = logoPath;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", gstNumber='" + gstNumber + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", logoPath='" + logoPath + '\'' +
                '}';
    }
}
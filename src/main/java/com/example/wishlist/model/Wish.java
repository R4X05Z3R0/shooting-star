package com.example.wishlist.model;

public class Wish {
    private int wishID;
    private String wishTitle;
    private String description;
    private String url;
    private String imageURL;
    private double price;
    private int wishlistID;

    //Constructors
    public Wish(int wishID, String wishTitle, double price, String description, String url, String imageURL, int wishlistID) {
        this.wishTitle = wishTitle;
        this.price = price;
        this.wishID = wishID;
        this.description = description;
        this.url = url;
        this.imageURL = imageURL;
        this.wishlistID = wishlistID;
    }
    public Wish(){}

    //Getters
    public int getWishID() {
        return wishID;
    }
    public String getWishTitle() {
        return wishTitle;
    }
    public String getDescription() {
        return description;
    }
    public String getUrl() {
        return url;
    }
    public double getPrice(){
        return price;
    }
    public String getImageURL() {
        return imageURL;
    }
    public int getWishlistID() {
        return wishlistID;
    }

    //Setters
    public void setWishID(int wishID) {
        this.wishID = wishID;
    }
    public void setWishTitle(String wishTitle) {
        this.wishTitle = wishTitle;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }
}

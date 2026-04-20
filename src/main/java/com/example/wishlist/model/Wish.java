package com.example.wishlist.model;

public class Wish {
    private int wishId;
    private String wishTitle;
    private String description;
    private String url;
    private String imageUrl;
    private double price;
    private int wishlistId;

    //Constructors
    public Wish(int wishId, String wishTitle, double price, String description, String url, String imageUrl, int wishlistID) {
        this.wishTitle = wishTitle;
        this.price = price;
        this.wishId = wishId;
        this.description = description;
        this.url = url;
        this.wishlistId = wishlistID;
    }
    public Wish(){}

    //Getters
    public int getWishId() {
        return wishId;
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
    public String getImageUrl() {
        return imageUrl;
    }
    public int getWishlistId() {
        return wishlistId;
    }

    //Setters
    public void setWishId(int wishId) {
        this.wishId = wishId;
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
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }
@Override
    public String toString() {
        return "Wish{" +
                "wishID=" + wishId +
                ", wishTitle='" + wishTitle + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", imageURL='" + imageUrl + '\'' +
                ", price=" + price +
                ", wishlistID=" + wishlistId +
                '}';
    }
}
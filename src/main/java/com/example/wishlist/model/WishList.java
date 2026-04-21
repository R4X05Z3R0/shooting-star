package com.example.wishlist.model;

public class WishList {

    private int wishlistId;
    private String title;
    private int userId;

    // Constructors
    public WishList(int wishlistId, String title, int userId) {
        this.wishlistId = wishlistId;
        this.title = title;
        this.userId = userId;
    }

    public WishList() {}

    // Getters
    public int getWishlistId() {
        return wishlistId;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }

    // Setters
    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
package com.example.wishlist.model;

//should we add description ?

public class WishList {
    private int wishlistID;
    private String title;
    private int userId;


    //Constructors
    public WishList(int wishlistID, String title, int userId) {
        this.wishlistID = wishlistID;
        this.title = title;
        this.userId = userId;
    }

    public WishList(){};

    //Getters
    public int getWishlistID() {
        return wishlistID;
    }
    public int getUserId() {
        return userId;
    }
    public String getTitle() {
        return title;
    }

    //Setters
    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


}


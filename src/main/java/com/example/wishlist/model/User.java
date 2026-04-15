package com.example.wishlist.model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String name;
    private String email;

    public User(int userId, String username, String password, String name, String email){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public User(String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }
    public User(){};

    //Getters
    public int getUserId(){
        return userId;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    //Setters
    public void setUserId(int userId){
        this.userId = userId;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

}

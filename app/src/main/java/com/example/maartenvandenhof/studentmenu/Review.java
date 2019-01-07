package com.example.maartenvandenhof.studentmenu;

public class Review {
    private String name;
    private int rating;
    private User user;

    public Review(String name, int rating, User user) {
        this.name = name;
        this.rating = rating;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

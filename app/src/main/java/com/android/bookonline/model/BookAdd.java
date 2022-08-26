package com.android.bookonline.model;


public class BookAdd {
    public String name;
    public String author;
    public String price;
    public String description;
    public String linkAvatar;

    public BookAdd() {
    }

    public BookAdd(String name, String author, String price, String description, String linkAvatar) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.linkAvatar = linkAvatar;
    }
}


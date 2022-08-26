package com.android.bookonline.model;

public class Book {
    public String pid;
    public String name;
    public String author;
    public String price;
    public String description;
    public String linkAvatar;

    public Book() {
    }

    public Book(String pid, String name, String author, String price, String description, String linkAvatar) {
        this.pid = pid;
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.linkAvatar = linkAvatar;
    }
}

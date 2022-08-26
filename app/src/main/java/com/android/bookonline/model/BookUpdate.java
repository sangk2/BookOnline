package com.android.bookonline.model;

import com.google.gson.annotations.SerializedName;

public class BookUpdate {
    @SerializedName("pid")
    public String pid;
    @SerializedName("name")
    public String name;
    @SerializedName("author")
    public String author;
    @SerializedName("price")
    public String price;
    @SerializedName("description")
    public String description;
    @SerializedName("linkAvatar")
    public String linkAvatar;

    public BookUpdate() {
    }

    public BookUpdate(String pid, String name, String author, String price, String description, String linkAvatar) {
        this.pid = pid;
        this.name = name;
        this.author = author;
        this.price = price;
        this.description = description;
        this.linkAvatar = linkAvatar;
    }
}

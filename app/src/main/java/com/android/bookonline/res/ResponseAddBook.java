package com.android.bookonline.res;

import com.android.bookonline.model.BookAdd;

public class ResponseAddBook {

    // Get
    private BookAdd books; // đặt tên giống tên bảng trong databse
    private String message;
    private String result;

    public BookAdd getBooks() {
        return books;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
}

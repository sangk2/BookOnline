package com.android.bookonline.res;

import com.android.bookonline.model.BookDelete;

public class ResponseDeleteBook {
    // get
    private BookDelete books;
    private String message;
    private String result;

    public BookDelete getBooks() {
        return books;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
}

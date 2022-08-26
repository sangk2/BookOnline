package com.android.bookonline.res;

import com.android.bookonline.model.BookUpdate;

public class ResponseUpdateBook {
    // get
    private BookUpdate books;
    private String message;
    private String result;

    public BookUpdate getBooks() {
        return books;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
}

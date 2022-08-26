package com.android.bookonline.res;

import com.android.bookonline.model.Book;

// class trung gian lấy dữ liệu từ class Book truyền vào RequestInterface
public class ResponseShowBook {
    private Book[] books; // books k đc đổi tên (để tên giống với dữ liệu trên API)
    private String message;
    private String result;

    public Book[] getBooks() {
        return books;
    }

    public String getMessage() {
        return message;
    }

    public String getResult() {
        return result;
    }
}

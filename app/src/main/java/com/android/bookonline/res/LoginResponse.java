package com.android.bookonline.res;

import com.android.bookonline.model.User;

public class LoginResponse {
    private String result;
    private String message;
    private User user;
    public String getResult() {
        return result;
    }
    public String getMessage() {
        return message;
    }
    public User getUser() {
        return user;
    }
}

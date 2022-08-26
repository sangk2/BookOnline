package com.android.bookonline.req;

import com.android.bookonline.model.User;

public class LoginRequest {
    private String operation;
    private User user;
    public void setOperation(String operation) {
        this.operation = operation;
    }
    public void setUser(User user) {
        this.user = user;
    }
}

package com.android.bookonline.interf;

import com.android.bookonline.req.LoginRequest;
import com.android.bookonline.res.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface loginInterface {
    @POST("login/")
    Call<LoginResponse> operation (@Body LoginRequest request);

}

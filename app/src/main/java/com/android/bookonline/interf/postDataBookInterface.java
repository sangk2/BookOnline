package com.android.bookonline.interf;

import com.android.bookonline.res.ResponseAddBook;
import com.android.bookonline.res.ResponseDeleteBook;
import com.android.bookonline.res.ResponseUpdateBook;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface postDataBookInterface {

    @FormUrlEncoded
    @POST("create_product.php")
    Call<ResponseAddBook> postAddSP(
            @Field("name") String name,
            @Field("author") String author,
            @Field("price") String price,
            @Field("description") String description,
            @Field("linkAvatar") String linkAvatar
    );

    @FormUrlEncoded
    @POST("update_product.php")
    Call<ResponseUpdateBook> postUpdateSP(
            @Field("pid") String pid,
            @Field("name") String name,
            @Field("author") String author,
            @Field("price") String price,
            @Field("description") String description,
            @Field("linkAvatar") String linkAvatar
    );

    @FormUrlEncoded
    @POST("delete_product.php")
    Call<ResponseDeleteBook> postDeleteSP(
            @Field("pid") String pid
    );
}

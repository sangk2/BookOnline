package com.android.bookonline.interf;

import com.android.bookonline.res.ResponseShowBook;

import retrofit2.Call;
import retrofit2.http.GET;

public interface getDataBookInterface {
    @GET("get_all_product.php")
    Call<ResponseShowBook> getAllProduct();

    @GET("get_product_detail.php")
    Call<ResponseShowBook> getDetailbyID();
}

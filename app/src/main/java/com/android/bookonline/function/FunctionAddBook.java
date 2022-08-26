package com.android.bookonline.function;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.bookonline.IPwifi;
import com.android.bookonline.res.ResponseAddBook;
import com.android.bookonline.interf.postDataBookInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FunctionAddBook {

    String ip = new IPwifi().ip;

    public void postAddSanPham(Context context, RecyclerView rvSP,
           String ten, String tacgia, String gia, String mota, String avatar){
        // gửi dữ liệu lên Server vào phương thức POST
        // lấy dữ liệu từ Dialog add
        // 1. Tạo đối tượng Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( ip+ "/android-network/BookOnline/product/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // 2. Gọi interface
        postDataBookInterface postDataInterface = retrofit.create(postDataBookInterface.class);
        // 2.2 chuẩn bị hàm
        Call<ResponseAddBook> call = postDataInterface.postAddSP(ten, tacgia, gia, mota, avatar);
        // 3. Thực thi hàm
        call.enqueue(new Callback<ResponseAddBook>() {
            @Override
            public void onResponse(Call<ResponseAddBook> call, Response<ResponseAddBook> response) {
                ResponseAddBook respPost = response.body();
                Toast.makeText(context,respPost.getMessage(),
                        Toast.LENGTH_LONG).show();

                // reload lại RecyclerView
                new getAllBooks().getALlSP(rvSP,context);
            }

            @Override
            public void onFailure(Call<ResponseAddBook> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),
                        Toast.LENGTH_LONG).show();
                Log.e("//===ErrAddSP: ",t.getMessage());
            }
        });

    }
}

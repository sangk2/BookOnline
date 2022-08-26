package com.android.bookonline.function;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.bookonline.IPwifi;
import com.android.bookonline.interf.postDataBookInterface;
import com.android.bookonline.req.RequestUpdateBook;
import com.android.bookonline.res.ResponseUpdateBook;
import com.android.bookonline.model.BookUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FunctionUpdateBook {

    String ip = new IPwifi().ip;

    public void postUpdateSanPham(BookUpdate bookUpdate, Context context, RecyclerView rvSP){
        //1. Tạo đối tượng
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( ip+ "/android-network/BookOnline/product/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // 2. Gọi hàm interface
        postDataBookInterface dataInterface = retrofit.create(postDataBookInterface.class);

        // 3. Đưa dữ liệu update vào request
        RequestUpdateBook requestUpdateBook = new RequestUpdateBook();
        requestUpdateBook.setBooks(bookUpdate);
        Call<ResponseUpdateBook> call = dataInterface.postUpdateSP(
                bookUpdate.pid,
                bookUpdate.name,
                bookUpdate.author,
                bookUpdate.price,
                bookUpdate.description,
                bookUpdate.linkAvatar
        );
        // 4. Thực thi
        call.enqueue(new Callback<ResponseUpdateBook>() {
            @Override
            public void onResponse(Call<ResponseUpdateBook> call, Response<ResponseUpdateBook> response) {
                ResponseUpdateBook resp = response.body();
                Toast.makeText(context,resp.getMessage(),
                        Toast.LENGTH_SHORT).show();

                // reload lại RecyclerView
                new getAllBooks().getALlSP(rvSP,context);
            }

            @Override
            public void onFailure(Call<ResponseUpdateBook> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                Log.e("===ErrUpdate: ", t.getMessage());
            }
        });
    }
}

package com.android.bookonline.function;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.bookonline.IPwifi;
import com.android.bookonline.req.RequestDeleteBook;
import com.android.bookonline.res.ResponseDeleteBook;
import com.android.bookonline.model.BookDelete;
import com.android.bookonline.interf.postDataBookInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FunctionDeleteBook {
    String ip = new IPwifi().ip;

    public void postDeleteSP(Context context, RecyclerView rvSP, String pid) {
        //1. Tạo đối tượng
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ip + "/android-network/BookOnline/product/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        // 2. Đưa dữ liệu delete vào request
        RequestDeleteBook requestDeleteBook = new RequestDeleteBook();
        requestDeleteBook.setBooks(new BookDelete(pid));

        // 3. Gọi hàm interface
        postDataBookInterface dataInterface = retrofit.create(postDataBookInterface.class);

        Call<ResponseDeleteBook> call = dataInterface.postDeleteSP(pid);
        // 4. Thực thi
        call.enqueue(new Callback<ResponseDeleteBook>() {
            @Override
            public void onResponse(Call<ResponseDeleteBook> call, Response<ResponseDeleteBook> response) {
                ResponseDeleteBook resp = response.body();
                Toast.makeText(context,resp.getMessage(),
                        Toast.LENGTH_SHORT).show();

                // reload lại RecyclerView
                new getAllBooks().getALlSP(rvSP,context);
            }

            @Override
            public void onFailure(Call<ResponseDeleteBook> call, Throwable t) {
                Toast.makeText(context, t.getMessage(),
                        Toast.LENGTH_SHORT).show();
                Log.e("===ErrDelete: ", t.getMessage());
            }
        });

    }
}

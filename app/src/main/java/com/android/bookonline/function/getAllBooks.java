package com.android.bookonline.function;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.bookonline.IPwifi;
import com.android.bookonline.interf.getDataBookInterface;
import com.android.bookonline.res.ResponseShowBook;
import com.android.bookonline.model.Book;
import com.android.bookonline.adapter.BookAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class getAllBooks {

    String ip = new IPwifi().ip;
    ArrayList<Book> SPList;

    public void getALlSP(RecyclerView rvSP, Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( ip+ "/android-network/BookOnline/product/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getDataBookInterface getDataInterface = retrofit.create(getDataBookInterface.class);

        Call<ResponseShowBook> call = getDataInterface.getAllProduct();

        call.enqueue(new Callback<ResponseShowBook>() {
            @Override
            public void onResponse(Call<ResponseShowBook> call, Response<ResponseShowBook> response) {
                ResponseShowBook resp = response.body();

                SPList = new ArrayList<>(Arrays.asList(resp.getBooks()));

                RecyclerView.LayoutManager manager = new GridLayoutManager(context, 2);
                rvSP.setLayoutManager(manager);
                BookAdapter adapter = new BookAdapter(SPList, context, rvSP);
                rvSP.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ResponseShowBook> call, Throwable t) {
                Log.e("//===ShowAll: ",t.getMessage());
            }
        });

    }
}

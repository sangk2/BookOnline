package com.android.bookonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.bookonline.function.FunctionAddBook;
import com.android.bookonline.function.getAllBooks;
import com.android.bookonline.model.Book;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvSP;
    FloatingActionButton btnAddSP;
    ArrayList<Book> SPList;

    Dialog dialog;
    EditText edTenSP, edTacGiaSP, edGiaSP, edMotaSP, edLinkAvatar;
    TextView tv_messageSP;
    Button btnCancelAddSP, btnSaveAddSP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSP = findViewById(R.id.rvSP);
        btnAddSP = findViewById(R.id.fbtnAddSP);

        SPList = new ArrayList<>();

        capNhatRV();

        btnAddSP.setOnClickListener(view -> {
            addSanPham(this);
        });
        rvSP.setOnClickListener(view -> {

        });
    }

    protected void addSanPham(final Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sp_add_dialog);
        edTenSP = dialog.findViewById(R.id.edTenSP);
        edTacGiaSP = dialog.findViewById(R.id.edTacGiaSP);
        edGiaSP = dialog.findViewById(R.id.edGiaSP);
        edMotaSP = dialog.findViewById(R.id.edMotaSP);
        edLinkAvatar = dialog.findViewById(R.id.edLinkAvatar);
        tv_messageSP = dialog.findViewById(R.id.tv_messageSP);
        btnCancelAddSP = dialog.findViewById(R.id.btnCancelSP);
        btnSaveAddSP = dialog.findViewById(R.id.btnSaveSP);

        btnSaveAddSP.setOnClickListener(view -> {
            String ten = edTenSP.getText().toString();
            String tacgia = edTacGiaSP.getText().toString();
            String gia = edGiaSP.getText().toString();
            String mota = edMotaSP.getText().toString();
            String avatar = edLinkAvatar.getText().toString();
            if (!ten.isEmpty() && !mota.isEmpty() && !gia.isEmpty() && !mota.isEmpty()){

                new FunctionAddBook().postAddSanPham(this, rvSP,ten, tacgia, gia, mota, avatar);

                dialog.dismiss();
            }else {
                tv_messageSP.setVisibility(View.VISIBLE);
                tv_messageSP.setText("Không được để trống");
            }
        });
        btnCancelAddSP.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    void capNhatRV(){
        new getAllBooks().getALlSP(rvSP,this);
    }

}


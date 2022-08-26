package com.android.bookonline.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.bookonline.MainActivity;
import com.android.bookonline.R;
import com.android.bookonline.interf.loginInterface;
import com.android.bookonline.model.User;
import com.android.bookonline.req.LoginRequest;
import com.android.bookonline.res.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText edEmaill, edPassl;
    Button btnDn, btnDk, btnQuenMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmaill = findViewById(R.id.edEmaill);
        edPassl = findViewById(R.id.edPassl);
        btnDk = findViewById(R.id.btnDk);
        btnDn = findViewById(R.id.btnDn);
        btnQuenMK = findViewById(R.id.btnQuenMK);

        SharedPreferences pre = getSharedPreferences("user",MODE_PRIVATE);
        edEmaill.setText(pre.getString(Constants.EMAIL,""));

        btnQuenMK.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ResetPasswordActivity.class));
        });

        btnDk.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
        btnDn.setOnClickListener(view -> {
            String email = edEmaill.getText().toString();
            String pass = edPassl.getText().toString();
            if(!email.isEmpty() && !pass.isEmpty()){
                loginProcess(email, pass);
            }
            else{
                Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void loginProcess(String email, String pass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginInterface requestInterface = retrofit.create(loginInterface.class);

        User user = new User();
        user.email = email;
        user.password = pass;

        LoginRequest request = new LoginRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);

        Call<LoginResponse> response = requestInterface.operation(request);
        response.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse resp = response.body();
                if (resp.getResult().equals(Constants.SUCCESS)){
                    SharedPreferences pref = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();

                    editor.putBoolean(Constants.IS_LOGGED_IN, true);
                    editor.putString(Constants.EMAIL, resp.getUser().email);
                    editor.putString(Constants.NAME, resp.getUser().name);
                    editor.putString(Constants.UNIQUE_ID, resp.getUser().unique_id);

                    editor.commit();
                    // Vào màn hình Main
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(Constants.TAG, "Failed");
            }
        });
    }
}
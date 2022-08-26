package com.android.bookonline.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class ResetPasswordActivity extends AppCompatActivity {

    TextView tvTimer;
    EditText edEmail4, edCode, edPass4;
    Button btnRest;

    String email;
    CountDownTimer countDownTimer;

    boolean isReset = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        tvTimer = findViewById(R.id.timer);
        edEmail4 = findViewById(R.id.edEmail4);
        edCode = findViewById(R.id.edCode);
        edPass4 = findViewById(R.id.edPass4);

        edCode.setVisibility(View.GONE);
        edPass4.setVisibility(View.GONE);
        tvTimer.setVisibility(View.GONE);

        btnRest = findViewById(R.id.btnReset);

        btnRest.setOnClickListener(view -> {
            if (!isReset){
                email = edEmail4.getText().toString();
                if (!email.isEmpty()){
                    initResetPassProcess(email);
                }else {
                    Toast.makeText(getApplicationContext(),"Không được để trống",
                            Toast.LENGTH_SHORT).show();
                }
            }else {
                String code = edCode.getText().toString();
                String pass = edPass4.getText().toString();
                if (!code.isEmpty() && !pass.isEmpty()){
                    finishResetPassProcess(email, code, pass);
                }else {
                    Toast.makeText(getApplicationContext(),"Không được để trống",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initResetPassProcess (String email){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginInterface requestInterface = retrofit.create(loginInterface.class);

        User user = new User();
        user.email = email;

        LoginRequest request = new LoginRequest();
        request.setOperation(Constants.RESET_PASSWORD_INITIATE);
        request.setUser(user);

        Call<LoginResponse> respon = requestInterface.operation(request);
        respon.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse resp = response.body();
                if (resp.getResult().equals(Constants.SUCCESS)){
                    edEmail4.setVisibility(View.GONE);
                    edCode.setVisibility(View.VISIBLE);
                    edPass4.setVisibility(View.VISIBLE);
                    tvTimer.setVisibility(View.VISIBLE);
                    btnRest.setText("Đổi mất khẩu");
                    isReset = true;

                    Toast.makeText(getApplicationContext(),"Nhận Code ở Email",
                            Toast.LENGTH_LONG).show();

                    startCountdownTimer();
                }else{
                    Toast.makeText(getApplicationContext(),"Email không tồn tại!",
                            Toast.LENGTH_LONG).show();
                    Log.d(Constants.TAG,"Email không tồn tại");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(Constants.TAG,t.getMessage());
            }
        });
    }

    private void finishResetPassProcess(String email, String code, String pass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginInterface requestInterface = retrofit.create(loginInterface.class);

        User user = new User();
        user.email = email;
        user.code = code;
        user.password = pass;

        LoginRequest request = new LoginRequest();
        request.setOperation(Constants.RESET_PASSWORD_FINISH);
        request.setUser(user);

        Call<LoginResponse> respon = requestInterface.operation(request);
        respon.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse resp = response.body();
                if (resp.getResult().equals(Constants.SUCCESS)){
                    countDownTimer.cancel();
                    isReset = false;
                    Toast.makeText(getApplicationContext(),"Đổi mật khẩu thành công!",
                            Toast.LENGTH_LONG).show();
                    goToLogin();
                }else{
                    Toast.makeText(getApplicationContext(),"Code không đúng",
                            Toast.LENGTH_LONG).show();
                    Log.d(Constants.TAG,"Code không đúng");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(Constants.TAG,t.getMessage());
            }
        });

    }
    private void startCountdownTimer(){
        countDownTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long l) {
                tvTimer.setText("Đang chờ: "+l/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Hết giờ! Yêu cầu lại để lấy mật khẩu",
                        Toast.LENGTH_LONG).show();
                goToLogin();
            }
        }.start();
    }
    private void goToLogin(){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
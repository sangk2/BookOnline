package com.android.bookonline.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.bookonline.IPwifi;
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

public class ProfileActivity extends AppCompatActivity {

    TextView tvName, tvEmail, tv_message;
    EditText edPassOld, edPassNew;
    Button btnChangePass, btnLogout, btnSave, btnCancel;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        btnChangePass = findViewById(R.id.btnChangePass);
        btnLogout = findViewById(R.id.btnLogout);

        SharedPreferences pref = getSharedPreferences("user",MODE_PRIVATE);
        tvName.setText("Xin chào "+pref.getString(Constants.NAME,""));
        tvEmail.setText(pref.getString(Constants.EMAIL,""));

        btnChangePass.setOnClickListener(view -> {
            ChangePassDialog(this);
        });
        btnLogout.setOnClickListener(view -> {
            Logout();
        });

    }
    public void Logout(){

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
    protected void ChangePassDialog(final Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_change_password);
        edPassOld = dialog.findViewById(R.id.edPassOld);
        edPassNew = dialog.findViewById(R.id.edPassNew);
        tv_message = dialog.findViewById(R.id.tv_message);
        btnSave = dialog.findViewById(R.id.btnSave);
        btnCancel = dialog.findViewById(R.id.btnCancel);

        btnSave.setOnClickListener(view -> {
            String old = edPassOld.getText().toString();
            String now = edPassNew.getText().toString();
            if (!old.isEmpty() && !now.isEmpty()){
                changePassProcess(tvEmail.getText().toString(), old, now);
            }else {
                tv_message.setVisibility(View.VISIBLE);
                tv_message.setText("Không được để trống");
            }
        });
        btnCancel.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void changePassProcess(String email, String oldPass, String newPass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        loginInterface requestInterface = retrofit.create(loginInterface.class);

        User user = new User();
        user.email = email;
        user.old_password = oldPass;
        user.new_password = newPass;

        LoginRequest request = new LoginRequest();
        request.setOperation(Constants.CHANGE_PASSWORD_OPERATION);
        request.setUser(user);

        Call<LoginResponse> responseCall = requestInterface.operation(request);
        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse resp = response.body();
                if (resp.getResult().equals(Constants.SUCCESS)){
                    tv_message.setVisibility(View.GONE);
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }else {
                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText(resp.getMessage());
                    Toast.makeText(getApplicationContext(),"Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(Constants.TAG, "failed "+t.getMessage());
                tv_message.setVisibility(View.VISIBLE);
                tv_message.setText(t.getMessage());
            }
        });
    }
}
class Constants{
    public static final String BASE_URL = new IPwifi().ip + "/android-network/BookOnline/";
    public static final String REGISTER_OPERATION = "register";
    public static final String LOGIN_OPERATION = "login";
    public static final String CHANGE_PASSWORD_OPERATION = "chgPass";
    public static final String SUCCESS = "success";
    public static final String FAILURE = "failure";
    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String UNIQUE_ID = "unique_id";
    public static final String TAG = "//====Tag";

    public static final String RESET_PASSWORD_INITIATE = "resPassReq";
    public static final String RESET_PASSWORD_FINISH = "resPass";

}

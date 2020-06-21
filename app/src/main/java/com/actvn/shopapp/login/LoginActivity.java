package com.actvn.shopapp.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Login;
import com.actvn.shopapp.api.model.ResultLogin;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.login.logout.LogoutActivity;
import com.actvn.shopapp.utils.ConstApp;
import com.actvn.shopapp.utils.ShareStoreUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private UserService userServic;
    private EditText edtEmail, edtPass;
    private TextView txtDismiss;
    private Button btn_Login;

    LinearLayout lnWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // neu token khac null thi chay toi main
        if (ShareStoreUtils.getToken(this) != null) {
            startMainActivity();
            return;
        }
        setContentView(R.layout.activity_login);
        // doan nay tim hieu roi note lai
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC.BASIC);
        client.addInterceptor(logging);
        //khoi tao retrofit
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create());
        //chay retrofit
        Retrofit retrofit = builder.build();
        //khoi tao phuong thuc trong userservice
        userServic = retrofit.create(UserService.class);
        innit();
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void innit() {
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btn_Login = (Button) findViewById(R.id.btnlogin);
        txtDismiss = findViewById(R.id.txtDismiss);
        lnWrong = findViewById(R.id.lnWrong);

       // btn_Login.setOnClickListener(this);
        txtDismiss.setOnClickListener(this);
    }

    public void login() {
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        Log.d("pass", pass);
        Login login = new Login(email, pass);
        Call<ResultLogin> call = userServic.login(login);
        call.enqueue(new Callback<ResultLogin>() {
            @Override
            public void onResponse(Call<ResultLogin> call, Response<ResultLogin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // neu ket noi thanh cong se luu token va chay toi man main
                    ShareStoreUtils.saveToken(LoginActivity.this, response.body().getAccessToken());
                    startMainActivity();
                } else {
                    lnWrong.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResultLogin> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "erros", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

        Toast.makeText(LoginActivity.this, "Login is failed", Toast.LENGTH_SHORT);

    }

    private void startMainActivity() {
        Intent intent = new Intent();
        intent.setClass(this, LogoutActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtDismiss:
                lnWrong.setVisibility(View.INVISIBLE);
                break;

        }
    }
}
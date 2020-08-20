package com.actvn.shopapp.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.actvn.shopapp.MainActivity;
import com.actvn.shopapp.R;
import com.actvn.shopapp.WellcomeActivity;
import com.actvn.shopapp.api.model.User;
import com.actvn.shopapp.api.service.UserService;
import com.actvn.shopapp.login.LoginActivity;
import com.actvn.shopapp.utils.ConstApp;
import com.actvn.shopapp.utils.ShareStoreUtils;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment {

    private LinearLayout lnLoginProfile;
    private View view;
    private LinearLayout lnInforProfile;
    private Button btnLogoutProfile;

    private TextView txtUserName;
    private TextView txtEmailProfile;
    private TextView txtPhoneProfile;
    private TextView txtAddressProfile;

    private UserService userService;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(ConstApp.BASE_URL)
                .client(client.build()).addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();
        userService = retrofit.create(UserService.class);

        loadJson();

    }

    private void initView() {
        lnLoginProfile = view.findViewById(R.id.lnLoginProfile);
        lnInforProfile = view.findViewById(R.id.lnInforProfile);
        btnLogoutProfile = view.findViewById(R.id.btnLogOutProfile);

        txtUserName = view.findViewById(R.id.txtUserName);
        txtEmailProfile = view.findViewById(R.id.txtEmailProfile);
        txtPhoneProfile = view.findViewById(R.id.txtPhoneProfile);
        txtAddressProfile = view.findViewById(R.id.txtAddressProfile);

        lnLoginProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnLogoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareStoreUtils.saveToken(getContext(), null);
                Intent intent = new Intent();
                intent.setClass(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        if (ShareStoreUtils.getToken(getContext()) != null) {
            lnLoginProfile.setVisibility(View.GONE);
            lnInforProfile.setVisibility(View.VISIBLE);
            btnLogoutProfile.setVisibility(View.VISIBLE);
        } else {
            lnLoginProfile.setVisibility(View.VISIBLE);
            lnInforProfile.setVisibility(View.GONE);
            btnLogoutProfile.setVisibility(View.GONE);

        }
    }

    private void loadJson() {
        Call<ResponseBody> call = userService.getSecret(ShareStoreUtils.getToken(getContext()));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                Toast.makeText(getContext(), String.valueOf(responseBody), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }
}

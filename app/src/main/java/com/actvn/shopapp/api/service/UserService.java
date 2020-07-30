package com.actvn.shopapp.api.service;


import com.actvn.shopapp.api.model.Login;
import com.actvn.shopapp.api.model.Register;
import com.actvn.shopapp.api.model.ResultLogin;
import com.actvn.shopapp.api.model.ResultRegister;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {

    @POST("/public/api/auth/login")
    Call<ResultLogin> login(@Body Login login);

    @POST("/public/api/auth/create")
    Call<ResultRegister> create(@Body Register register);

    @GET("/public/api/auth/user")
    Call<ResponseBody> getSecret(@Header("Authorization") String access_token);


}

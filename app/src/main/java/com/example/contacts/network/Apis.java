package com.example.contacts.network;

import com.example.contacts.network.models.ResponseLogin;
import com.example.contacts.network.models.User;
import com.example.contacts.network.models.UserRegister;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Apis {

    @POST("login")
    Call<ResponseLogin> login(@Body User user);

    @POST("register")
    Call<ResponseLogin> register(@Body User user);
}

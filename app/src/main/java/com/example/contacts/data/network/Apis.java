package com.example.contacts.data.network;

import com.example.contacts.data.network.models.ContactsModel.AddContact;
import com.example.contacts.data.network.models.ContactsModel.ResponseContact;
import com.example.contacts.data.network.models.ResponseLogin;
import com.example.contacts.data.network.models.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Apis {

    @POST("login")
    Call<ResponseLogin> login(@Body User user);

    @POST("register")
    Call<ResponseLogin> register(@Body User user);

    @GET("contacts")
    Call<ResponseContact> getContacts();

    @DELETE("contacts/{id}")
    Call<ResponseBody> deleteItem(@Path("id") int itemId);

    @POST("contacts")
    Call<AddContact> add(@Body AddContact contact);
}

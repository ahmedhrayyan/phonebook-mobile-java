package com.example.contacts.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSource {
    private static final String BASE_URL = "https://phonebook-be.herokuapp.com/api/";

    public static Apis getSOService() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Apis.class);
    }
}

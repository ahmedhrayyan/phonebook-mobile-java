package com.example.contacts.data.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiSource {

    private static final String BASE_URL = "https://phonebook-be.herokuapp.com/api/";

    public static Apis getSOService() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Apis.class);
    }

    public static Apis getSOServiceWithAuth(String token) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token).build();
            return chain.proceed(request);
        });
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).client(httpClient.build()).build().create(Apis.class);
    }
}

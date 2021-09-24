package com.example.contacts.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.contacts.network.ApiSource;
import com.example.contacts.network.models.ResponseLogin;
import com.example.contacts.network.models.User;
import com.example.contacts.network.models.UserRegister;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static final String TAG = AuthRepository.class.getName();

    public LiveData<ResponseLogin> userLogin(String email, String password) {

        MutableLiveData<ResponseLogin> liveData = new MutableLiveData<>();
        ApiSource.getSOService().login(new User(email, password))
                .enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if (response.isSuccessful()) {
                            liveData.setValue(response.body());
                        } else {
                            ResponseLogin r = new ResponseLogin();
                            r.setToken(String.valueOf(response.code()));
                            Log.e(TAG, "onResponse: " + r.getToken());
                            liveData.setValue(r);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        //TODO: handle Error.
                    }
                });
        return liveData;
    }

    public LiveData<ResponseLogin> userRegister(String name, String email, String password) {

        MutableLiveData<ResponseLogin> liveData = new MutableLiveData<>();
        ApiSource.getSOService().register(new User(email, password, name))
                .enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        if (response.isSuccessful()) {
                            liveData.setValue(response.body());
                        } else {
                            ResponseLogin r = new ResponseLogin();
                            try {
                                r.setToken(String.valueOf(response.errorBody().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Log.e(TAG, "onResponse: " + r.getToken());
                            liveData.setValue(r);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        //TODO: handle Error.
                    }
                });
        return liveData;
    }
}

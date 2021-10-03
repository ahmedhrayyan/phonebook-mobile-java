package com.example.contacts.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.contacts.data.network.ApiSource;
import com.example.contacts.data.network.models.ContactsModel.AddContact;
import com.example.contacts.data.network.models.ContactsModel.AddPhone;
import com.example.contacts.data.network.models.ContactsModel.ResponseContact;
import com.example.contacts.data.network.models.ResponseLogin;
import com.example.contacts.data.network.models.User;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactRepository {
    private static final String TAG = ContactRepository.class.getName();

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

    public LiveData<ResponseContact> getContacts(String token) {

        MutableLiveData<ResponseContact> liveData = new MutableLiveData<>();
        ApiSource.getSOServiceWithAuth(token).getContacts().enqueue(new Callback<ResponseContact>() {
            @Override
            public void onResponse(Call<ResponseContact> call, Response<ResponseContact> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    Log.e(TAG, "Get Contact onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseContact> call, Throwable t) {

            }
        });
        return liveData;
    }

    public boolean deleteItem(int id, String token) {
        boolean[] deleted = {true};
        ApiSource.getSOServiceWithAuth(token).deleteItem(id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                deleted[0] = response.isSuccessful();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                deleted[0] = false;
            }
        });
        return deleted[0];
    }

    public LiveData<AddContact> addContact(String token, String email, String name, String phoneNumber) {
        MutableLiveData<AddContact> liveData = new MutableLiveData<>();
        ArrayList<AddPhone> phones = new ArrayList<>();
        phones.add(new AddPhone(1, phoneNumber));
        AddContact contact = new AddContact(email, name, phones);
        ApiSource.getSOServiceWithAuth(token).add(contact).enqueue(new Callback<AddContact>() {
            @Override
            public void onResponse(Call<AddContact> call, Response<AddContact> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(response.body());
                } else {
                    try {
                        assert response.errorBody() != null;
                        Log.e(TAG, "Response : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AddContact> call, Throwable t) {

            }
        });
        return liveData;
    }
}

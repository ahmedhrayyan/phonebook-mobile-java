package com.example.contacts.ui;

import androidx.lifecycle.LiveData;

import com.example.contacts.network.models.ResponseLogin;

public interface AuthListener {

    void onStarted();

    void onSuccess(LiveData<ResponseLogin> liveData);

    void onFailure(String msg);

    boolean isConnection();
}

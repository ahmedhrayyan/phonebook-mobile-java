package com.example.contacts.ui.home.Contacts;

import androidx.lifecycle.LiveData;

import com.example.contacts.data.network.models.ContactsModel.ResponseContact;

public interface ContactListener {

    void onStarted();

    void onSuccess(LiveData<ResponseContact> liveData);

    void onFailure(String msg);

    boolean isConnection();
}

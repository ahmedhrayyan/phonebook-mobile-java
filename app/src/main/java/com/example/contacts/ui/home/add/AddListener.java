package com.example.contacts.ui.home.add;

import androidx.lifecycle.LiveData;

import com.example.contacts.data.network.models.ContactsModel.AddContact;

public interface AddListener {
    void onStarted();

    void onSuccess(LiveData<AddContact> liveData);

    void onFailure(String msg);

    boolean isConnection();
}

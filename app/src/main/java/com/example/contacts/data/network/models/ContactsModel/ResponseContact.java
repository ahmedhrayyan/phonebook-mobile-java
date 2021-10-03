package com.example.contacts.data.network.models.ContactsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseContact {

    @SerializedName("data")
    @Expose
    private List<Contact> contactList;

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}

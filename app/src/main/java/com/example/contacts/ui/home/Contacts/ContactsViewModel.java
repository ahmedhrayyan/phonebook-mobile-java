package com.example.contacts.ui.home.Contacts;

import androidx.lifecycle.ViewModel;

import com.example.contacts.data.repository.ContactRepository;
import com.example.contacts.ui.home.Contacts.ContactListener;

public class ContactsViewModel extends ViewModel {
    ContactListener contactListener;

    protected void getContacts(String token) {
        if (!contactListener.isConnection()) {
            contactListener.onFailure("No Internet Connection");
            return;
        }
        contactListener.onStarted();
        contactListener.onSuccess(new ContactRepository().getContacts(token));
    }

    protected boolean delete(int id, String token) {
        if (!contactListener.isConnection()) {
            contactListener.onFailure("No Internet Connection");
            return false;
        }
        contactListener.onStarted();
        boolean b = new ContactRepository().deleteItem(id, token);
        if (!b) {
            contactListener.onFailure("Can't Delete this item right now.");
        }
        return b;
    }
}

package com.example.contacts.ui.home.add;

import android.util.Patterns;

import androidx.lifecycle.ViewModel;

import com.example.contacts.data.repository.ContactRepository;

public class AddViewModel extends ViewModel {
    AddListener addListener;

    protected void add(String token, String name, String email, String phone) {
        if (!addListener.isConnection()) {
            addListener.onFailure("No Internet Connection");
            return;
        }
        if (name.isEmpty()) {
            addListener.onFailure("Please write Your name");
            return;
        }
        if (email.isEmpty()) {
            addListener.onFailure("Please write Your Email.");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            addListener.onFailure("Please write Your Email Correctly.");
            return;
        }
        if (phone.isEmpty() || phone.length() < 8) {
            addListener.onFailure("Phone should have at least 8 digits");
            return;
        }
        addListener.onStarted();
        addListener.onSuccess(new ContactRepository().addContact(token, email, name, phone));
    }
}
package com.example.contacts.ui.register;

import androidx.lifecycle.ViewModel;

import com.example.contacts.R;
import com.example.contacts.repository.AuthRepository;
import com.example.contacts.ui.AuthListener;

public class RegisterViewModel extends ViewModel {
    AuthListener authListener;

    protected void RegisterRequest(String email, String name, String password) {
        if (!authListener.isConnection()) {
            return;
        }
        if (name.isEmpty()) {
            authListener.onFailure(String.valueOf(R.string.write_name));
            return;
        }
        if (email.isEmpty()) {
            authListener.onFailure(String.valueOf(R.string.write_email));
            return;
        }
        if (password.isEmpty()) {
            authListener.onFailure(String.valueOf(R.string.write_password));
            return;
        }
        authListener.onStarted();
        authListener.onSuccess(new AuthRepository().userRegister(email, name, password));
    }
}

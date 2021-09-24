package com.example.contacts.ui.login;

import androidx.lifecycle.ViewModel;

import com.example.contacts.R;
import com.example.contacts.repository.AuthRepository;
import com.example.contacts.ui.AuthListener;

public class LoginViewModel extends ViewModel {
    AuthListener authListener;

    protected void loginRequest(String email, String password) {
        if (!authListener.isConnection()) {
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
        authListener.onSuccess(new AuthRepository().userLogin(email, password));
    }
}

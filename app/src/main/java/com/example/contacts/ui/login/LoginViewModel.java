package com.example.contacts.ui.login;

import androidx.lifecycle.ViewModel;

import com.example.contacts.R;
import com.example.contacts.repository.AuthRepository;
import com.example.contacts.ui.AuthListener;

public class LoginViewModel extends ViewModel {
    AuthListener authListener;

    protected void loginRequest(String email, String password) {
        if (!authListener.isConnection()) {
            authListener.onFailure("No Internet Connection");
            return;
        }
        if (email.isEmpty()) {
            authListener.onFailure("Please write Your Email.");
            return;
        }
        if (password.isEmpty()) {
            authListener.onFailure("Please write Your Password");
            return;
        }
        authListener.onStarted();
        authListener.onSuccess(new AuthRepository().userLogin(email, password));
    }
}

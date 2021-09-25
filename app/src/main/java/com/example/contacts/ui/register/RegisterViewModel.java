package com.example.contacts.ui.register;

import android.util.Patterns;

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
            authListener.onFailure("Please write Your Name.");
            return;
        }
        if (email.isEmpty()) {
            authListener.onFailure("Please write Your Email");
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            authListener.onFailure("Please write Your Email Correctly.");
            return;
        }
        if (password.isEmpty() || password.length() < 8) {
            authListener.onFailure("Password should have at least 8 characters");
            return;
        }
        authListener.onStarted();
        authListener.onSuccess(new AuthRepository().userRegister(email, name, password));
    }
}

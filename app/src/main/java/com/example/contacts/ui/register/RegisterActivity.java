package com.example.contacts.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;

import com.example.contacts.R;
import com.example.contacts.data.local.LocalData;
import com.example.contacts.databinding.ActivityRegisterBinding;
import com.example.contacts.data.network.models.ResponseLogin;
import com.example.contacts.ui.AuthListener;
import com.example.contacts.ui.home.Contacts.ContactsActivity;
import com.example.contacts.utils.NetworkConnection;
import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity implements AuthListener {

    private ActivityRegisterBinding binding;
    private LocalData localData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localData = LocalData.getPreferences(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        RegisterViewModel registerVM = new RegisterViewModel();
        registerVM.authListener = this;
        binding.setRegisterview(registerVM);
        binding.setRegisterPresenter(() -> {
            final String name = binding.nameEt.getText().toString().trim();
            final String email = binding.emailEt.getText().toString().trim();
            final String password = binding.passwordEt.getText().toString().trim();
            registerVM.RegisterRequest(email, name, password);
        });
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(LiveData<ResponseLogin> liveData) {
        liveData.observe(this, s -> {
            if (s.getToken().equals("400")) {
                Snackbar.make(findViewById(R.id.register_bt), "Email or Password is invalid",
                        Snackbar.LENGTH_LONG).show();
            } else {
                //TODO:Store the given token.
                Snackbar.make(findViewById(R.id.register_bt), s.getToken(), Snackbar.LENGTH_LONG).show();
                Intent i = new Intent(this, ContactsActivity.class);
                localData.setTOKEN(s.getToken());
                startActivity(i);
            }
        });
    }

    @Override
    public void onFailure(String msg) {
        Snackbar.make(findViewById(R.id.register_bt), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isConnection() {
        return new NetworkConnection(this).isInternetAvailable();
    }
}
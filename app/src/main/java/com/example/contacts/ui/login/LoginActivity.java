package com.example.contacts.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

import com.example.contacts.databinding.ActivityLoginBinding;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.contacts.R;
import com.example.contacts.network.models.ResponseLogin;
import com.example.contacts.ui.AuthListener;
import com.example.contacts.ui.register.RegisterActivity;
import com.example.contacts.utils.NetworkConnection;

public class LoginActivity extends AppCompatActivity implements AuthListener {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Prevent Dark Mode.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginVM = new LoginViewModel();
        loginVM.authListener = this;
        binding.setLoginview(loginVM);
        binding.setLoginPresenter(new LoginPresenter() {
            @Override
            public void login() {
                final String email = binding.emailEt.getText().toString().trim();
                final String password = binding.passwordEt.getText().toString().trim();
                loginVM.loginRequest(email, password);
            }

            @Override
            public void register() {
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStarted() {
        Toast.makeText(this, "Start", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess(LiveData<ResponseLogin> liveData) {
        liveData.observe(this, s -> {
            // TODO: handle Wrong email or Password code(422).
            Toast.makeText(this, s.getToken(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isConnection() {
        return new NetworkConnection(this).isInternetAvailable();
    }
}
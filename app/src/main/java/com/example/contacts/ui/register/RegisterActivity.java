package com.example.contacts.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.widget.Toast;

import com.example.contacts.R;
import com.example.contacts.databinding.ActivityRegisterBinding;
import com.example.contacts.network.models.ResponseLogin;
import com.example.contacts.ui.AuthListener;
import com.example.contacts.utils.NetworkConnection;

public class RegisterActivity extends AppCompatActivity implements AuthListener {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
package com.example.contacts.ui.home.add;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.contacts.R;
import com.example.contacts.data.local.LocalData;
import com.example.contacts.data.network.models.ContactsModel.AddContact;
import com.example.contacts.databinding.ActivityAddContactBinding;
import com.example.contacts.ui.home.Contacts.ContactsActivity;
import com.example.contacts.utils.NetworkConnection;
import com.google.android.material.snackbar.Snackbar;

public class AddContactActivity extends AppCompatActivity implements AddListener {
    private LocalData localData;
    private ActivityAddContactBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact);
        localData = LocalData.getPreferences(this);
        AddViewModel AddContactsVM = new AddViewModel();
        AddContactsVM.addListener = this;
        binding.setAddview(AddContactsVM);
        binding.setAddPresenter(() -> {
            final String email = binding.emailContactEt.getText().toString().trim();
            final String name = binding.nameContactEt.getText().toString().trim();
            final String phone = binding.phoneContactEt.getText().toString().trim();
            AddContactsVM.add(localData.getTOKEN(), name, email, phone);
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent i = new Intent(this, ContactsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(LiveData<AddContact> liveData) {
        liveData.observe(this, data -> {
            Intent i = new Intent(this, ContactsActivity.class);
            startActivity(i);
        });
    }


    @Override
    public void onFailure(String msg) {
        Snackbar.make(findViewById(R.id.add_contact_bt), msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean isConnection() {
        return new NetworkConnection(this).isInternetAvailable();
    }
}
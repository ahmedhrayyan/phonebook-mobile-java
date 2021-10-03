package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.example.contacts.data.local.LocalData;
import com.example.contacts.ui.home.Contacts.ContactsActivity;
import com.example.contacts.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Prevent Dark Mode.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        LocalData localData = LocalData.getPreferences(this);
        Intent i;
        if (localData.getTOKEN() == null) {
            i = new Intent(this, LoginActivity.class);
        } else {
            i = new Intent(this, ContactsActivity.class);
        }
        startActivity(i);
    }
}
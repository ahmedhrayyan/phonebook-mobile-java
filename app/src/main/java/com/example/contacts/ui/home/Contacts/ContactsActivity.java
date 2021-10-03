package com.example.contacts.ui.home.Contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.contacts.R;
import com.example.contacts.data.local.LocalData;
import com.example.contacts.data.network.models.ContactsModel.ResponseContact;
import com.example.contacts.ui.home.add.AddContactActivity;
import com.example.contacts.ui.login.LoginActivity;
import com.example.contacts.utils.NetworkConnection;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class ContactsActivity extends AppCompatActivity implements ContactListener {
    private LocalData localData;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        localData = LocalData.getPreferences(this);

        recyclerView = findViewById(R.id.recyclerView_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ContactsViewModel ContactsVM = new ContactsViewModel();
        ContactsVM.contactListener = this;
        FloatingActionButton bt = findViewById(R.id.add_contact_FB);
        bt.setOnClickListener(v -> startActivity(new Intent(this, AddContactActivity.class)));

        ContactsVM.getContacts(localData.getTOKEN());
    }


    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(LiveData<ResponseContact> liveData) {
        liveData.observe(this, s -> {
            adapter = new ContactAdapter(this, s.getContactList());
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isConnection() {
        return new NetworkConnection(this).isInternetAvailable();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout_icon) {
            exit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exit() {
        new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    Intent settingsIntent = new Intent(this, LoginActivity.class);
                    localData.setTOKEN(null);
                    startActivity(settingsIntent);
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                .show();
    }
}
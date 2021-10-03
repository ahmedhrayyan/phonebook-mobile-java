package com.example.contacts.ui.home.Contacts;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.R;
import com.example.contacts.data.local.LocalData;
import com.example.contacts.data.network.models.ContactsModel.Contact;
import com.example.contacts.data.network.models.ContactsModel.ResponseContact;
import com.example.contacts.utils.NetworkConnection;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements ContactListener {
    private final Context context;
    private final List<Contact> contactList;
    private final ContactsViewModel ContactsVM;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        ContactsVM = new ContactsViewModel();
        ContactsVM.contactListener = this;

    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact user = contactList.get(position);

        holder.txName.setText(user.getName());
        holder.txEmail.setText(user.getEmail());
        holder.imDelete.setOnClickListener(v -> new AlertDialog.Builder(context)
                .setTitle("Delete Contact")
                .setMessage("Are you sure you want to delete this Contact?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    LocalData localData = LocalData.getPreferences(context);
                    if (ContactsVM.delete(user.getId(), localData.getTOKEN())) {
                        contactList.remove(position);
                        Snackbar.make(v, "Deleted Successfully", Snackbar.LENGTH_LONG).show();
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onSuccess(LiveData<ResponseContact> liveData) {

    }

    @Override
    public void onFailure(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isConnection() {
        return new NetworkConnection(context).isInternetAvailable();
    }


    static class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView txName, txEmail;
        ImageView imEdit, imDelete;

        public ContactViewHolder(View itemView) {
            super(itemView);
            txName = itemView.findViewById(R.id.tx_name_of_contact);
            txEmail = itemView.findViewById(R.id.tx_email_of_contact);
            imEdit = itemView.findViewById(R.id.edit_img);
            imDelete = itemView.findViewById(R.id.delete_img);
        }
    }
}

package com.example.contacts.data.network.models.ContactsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AddContact {
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("notes")
    @Expose
    private String notes;

    @SerializedName("phones")
    @Expose
    private List<AddPhone> phones;

    public AddContact(String email, String name, List<AddPhone> phones) {
        this.email = email;
        this.name = name;
        this.phones = phones;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<AddPhone> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<AddPhone> phones) {
        this.phones = phones;
    }


}

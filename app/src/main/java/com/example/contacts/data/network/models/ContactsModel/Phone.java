package com.example.contacts.data.network.models.ContactsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {


    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("type_id")
    @Expose
    private long typeId;

    @SerializedName("value")
    @Expose
    private String value;

    public Phone(long typeId, String value) {
        this.typeId = typeId;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

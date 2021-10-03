package com.example.contacts.data.network.models.ContactsModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPhone {

    @SerializedName("type_id")
    @Expose
    private long typeId;

    @SerializedName("value")
    @Expose
    private String value;

    public AddPhone(long typeId, String value) {
        this.typeId = typeId;
        this.value = value;
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

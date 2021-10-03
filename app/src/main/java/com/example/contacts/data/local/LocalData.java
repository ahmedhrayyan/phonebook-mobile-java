package com.example.contacts.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalData {
    private static final String PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN";

    private static final String FILE_NAME = "PREF";
    private SharedPreferences mPrefs = null;
    private static SharedPreferences.Editor editor = null;
    private static LocalData LocalData;

    public LocalData(Context context) {
        mPrefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        editor = mPrefs.edit();
        editor.apply();

    }

    public static LocalData getPreferences(Context context) {
        if (LocalData == null) LocalData = new LocalData(context);
        return LocalData;
    }

    public String getTOKEN() {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null);
    }

    public void setTOKEN(String token) {
        editor.putString(PREF_KEY_ACCESS_TOKEN, token).apply();
    }

}

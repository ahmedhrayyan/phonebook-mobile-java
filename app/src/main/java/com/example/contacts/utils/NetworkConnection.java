package com.example.contacts.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnection {
    private final Context context;

    public NetworkConnection(Context context) {
        this.context = context;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager con = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (con != null) {
            NetworkInfo info = con.getActiveNetworkInfo();
            return info != null && info.isConnected();

        }
        return false;
    }
}

package com.example.arquiteturasw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("FJMS", "recebemos o RECEIVE_BOOT_COMPLETED ");
    }
}
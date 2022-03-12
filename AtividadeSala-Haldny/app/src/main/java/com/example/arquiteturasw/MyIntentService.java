package com.example.arquiteturasw;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    public MyIntentService() {

        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {

            String name = intent.getStringExtra("Tela");
//            Toast.makeText(this,"Nome da tela:" + name, Toast.LENGTH_LONG).show();
            Log.d("HSS", "chegou aqui ");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("HSS", "Service onDestroy: ");
    }
}
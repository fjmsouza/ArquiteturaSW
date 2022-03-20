package com.example.arquiteturasw;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.List;

public class Tela2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        Button botaoGo = findViewById(R.id.button2);

        List<MyContact> contacts = ContactsHelper.getContacts(this);
        Log.d("FJMS","ID: "+ contacts.get(1).getId() +" ,Name: "+contacts.get(1).getName());

        botaoGo.setOnClickListener(
                v -> {
                    startActivity(new Intent(this,Tela3.class));
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("Tela", "Tela 2");
        startService(intent);
    }
}
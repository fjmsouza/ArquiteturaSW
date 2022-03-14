package com.example.arquiteturasw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGo = findViewById(R.id.button2);


        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("Tela", "Tela 1");
        startService(intent);

        btnGo.setOnClickListener(
                v -> {
                    startActivity(new Intent(this,Tela2.class));
                }
        );

        List<MyContact> contacts = ContactsHelper.getContacts(this);

        Log.d("FJMS","ID: "+ contacts.get(0).getId() +" ,Name: "+contacts.get(0).getName());

    }

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new MyBroadcastReceiver();
//        tipo de mensagem q vou ouvir, pois tenho este PODER de tratá-la!
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
//        registrando aqui que mudança quero ouvir, pois não está sendo mais pelo manifesto, o app
//        precisa estar ativo para ouvir.
        registerReceiver(receiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        limpando memória
        if (receiver != null){
            unregisterReceiver(receiver);
//            força o garbagecollector
            receiver = null;
        }
    }

    class MyBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("FJMS", "O status do wi-fi mudou");
        }
    }
}
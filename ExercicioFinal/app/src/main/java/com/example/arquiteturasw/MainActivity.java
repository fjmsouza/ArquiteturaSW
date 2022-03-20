package com.example.arquiteturasw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
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

        btnGo.setOnClickListener(
                v -> {
                    startActivity(new Intent(this,Tela2.class));
                }
        );


        final PackageManager pm = getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.enabled){
                Log.d("FJMS", "App Instalado: " + packageInfo.packageName +" - Habilitado");
            }
            else {
                Log.d("FJMS", "App Instalado: " + packageInfo.packageName +" - Desabilitado");
            }

            Log.d("FJMS", "     ");
        }
// the getLaunchIntentForPackage returns an intent that you can use with startActivity()

    }

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new MyBroadcastReceiver();
//        tipo de mensagem q vou ouvir, pois tenho este PODER de tratá-la!
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        IntentFilter filter1 = new IntentFilter(String.valueOf(WifiManager.WIFI_STATE_ENABLED));
        IntentFilter filter2 = new IntentFilter(String.valueOf(WifiManager.WIFI_STATE_DISABLED));

//        registrando aqui que mudança quero ouvir, pois não está sendo mais pelo manifesto, o app
//        precisa estar ativo para ouvir.
        registerReceiver(receiver,filter);
        registerReceiver(receiver,filter1);
        registerReceiver(receiver,filter2);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("Tela", "Tela 1");
        startService(intent);
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
            WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            if (wifi.isWifiEnabled()){
                Log.d("FJMS", "Wi-fi habilitado");
            }
            else {
                Log.d("FJMS", "Wi-fi desabilitado");
            }
        }
    }
}
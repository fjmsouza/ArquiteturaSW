package com.example.arquiteturasw;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ApplicationErrorReport;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

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

        List<MyContact> contacts = ContactsHelper.getContacts(this);
        Log.d("FJMS","ID: "+ contacts.get(0).getId() +" ,Name: "+contacts.get(0).getName());
        Toast.makeText(this, "ID: "+ contacts.get(0).getId() +" ,Name: "+contacts.get(0).getName(), Toast.LENGTH_SHORT).show();

//        tipo de mensagem q vou ouvir, pois tenho este PODER de tratá-la!
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        IntentFilter filter1 = new IntentFilter(String.valueOf(WifiManager.WIFI_STATE_ENABLED));
        IntentFilter filter2 = new IntentFilter(String.valueOf(WifiManager.WIFI_STATE_DISABLED));
        IntentFilter filter3 = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

//        registrando aqui que mudança quero ouvir, pois não está sendo mais pelo manifesto, o app
//        precisa estar ativo para ouvir.
        registerReceiver(receiver,filter);
        registerReceiver(receiver,filter1);
        registerReceiver(receiver,filter2);
        registerReceiver(receiver,filter3);

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
        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void onReceive(Context context, Intent intent) {
            WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            BatteryManager batManager = (BatteryManager) getSystemService(context.BATTERY_SERVICE);

            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            float batteryPct = level * 100 / (float)scale;

            int status1 = intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
            int status2 = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
            float timeToCharge = batManager.computeChargeTimeRemaining();

            boolean statusCharge = status1 == BatteryManager.BATTERY_STATUS_CHARGING;
            boolean statusHealth1 = status2 == BatteryManager.BATTERY_HEALTH_GOOD;
            boolean statusHealth2 = status2 == BatteryManager.BATTERY_HEALTH_DEAD;
            boolean statusHealth3 = status2 == BatteryManager.BATTERY_HEALTH_OVERHEAT;
            boolean statusHealth4 = status2 == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE;

            Log.d("FJMS", "Bateria "+ Double.toString(batteryPct) + "%");
            if (statusCharge) {
                Log.d("FJMS", "Bateria carregando");

                if (timeToCharge != -1){
                    timeToCharge = timeToCharge/60000;
                    Log.d("FJMS", "Tempo restante de carregamento: "+ Math.round(timeToCharge) + " min");
                }

                if (statusHealth1){
                    Log.d("FJMS", "Bateria - Ok");
                }
                else {
                    Log.d("FJMS", "Bateria - Ruim");

                }
                if (statusHealth2){
                    Log.d("FJMS", "Bateria - Morta");

                }
                if (statusHealth3){
                    Log.d("FJMS", "Bateria - Sobreaquecida");

                }
                if (statusHealth4){
                    Log.d("FJMS", "Bateria - Sobretensão");

                }

            }

            if (wifi.isWifiEnabled()){
                Log.d("FJMS", "Wi-fi habilitado");
            }
            else {
                Log.d("FJMS", "Wi-fi desabilitado");
            }
        }
    }
}
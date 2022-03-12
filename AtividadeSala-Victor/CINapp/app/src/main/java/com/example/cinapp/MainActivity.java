package com.example.cinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLog.debug("devlog", "on Create - MainActivity");

        ((Button) findViewById(R.id.button2)).setOnClickListener(v -> {
            Log.d("devlog", "on click on button 1");
            Intent intent = new Intent(this, MyActivity.class);
            startActivity(intent);
        });

        ((Button) findViewById(R.id.button)).setOnClickListener(v -> {
            Log.d("devlog", "on click on button 2");
            Intent intent = new Intent(this, MyService1.class);
            //acrescentei
            intent.putExtra("TEXTO","EITA!!");
            startService(intent);
        });

        ((Button) findViewById(R.id.button3)).setOnClickListener(v -> {
            Log.d("devlog", "on click on button 3");
            Intent intent = new Intent(this, MyService2.class);
//            intent.getExtras().putString("TEXTO", "Hello Wolrd!");
            intent.putExtra("TEXTO", "Hello World!");
            startService(intent);
        });

        ((Button) findViewById(R.id.button4)).setOnClickListener(v -> {
            Log.d("devlog", "on click on button 4");
            Intent intent = new Intent(this, MyLocationActivity.class);
            startActivity(intent);
        });
    }
}
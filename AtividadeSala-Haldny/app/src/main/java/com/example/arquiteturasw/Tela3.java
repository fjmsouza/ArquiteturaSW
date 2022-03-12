package com.example.arquiteturasw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Tela3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("Tela", "Tela 3");
        startService(intent);
    }

}
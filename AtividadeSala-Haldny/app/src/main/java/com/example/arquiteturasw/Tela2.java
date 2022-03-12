package com.example.arquiteturasw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Tela2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);
        Button botaoGo = findViewById(R.id.button2);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("Tela", "Tela 2");
        startService(intent);

        botaoGo.setOnClickListener(
                v -> {
                    startActivity(new Intent(this,Tela3.class));
                }
        );

    }
}
package com.example.arquiteturasw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class Tela3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);

        List<MyContact> contacts = ContactsHelper.getContacts(this);
//        Log.d("FJMS","ID: "+ contacts.get(2).getId() +" ,Name: "+contacts.get(2).getName());
        String contato = "ID: "+ contacts.get(2).getId() +" ,Name: "+contacts.get(2).getName();

        Toast.makeText(this, contato, Toast.LENGTH_SHORT).show();
        Log.d("FJMS", contato);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("Tela", "Tela 3");
        startService(intent);
    }

}
package com.example.cinapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

public class MyLocationActivity extends Activity implements LocationListener {

    public double latitude;
    public double longitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }else{
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }

        }

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null){
            Log.d("devlog", "gps ativado");
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            updateLocationText(location, latitude, longitude);
        }
        else{
            Log.d("devlog", "gps desativado");
        }


    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        updateLocationText(location, latitude, longitude);
    }

    private void updateLocationText(Location location,double latitude, double longitude) {
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText("Latitude:" + String.valueOf(latitude) + ", Longitude:" + String.valueOf(longitude));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:{
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Permissão Garantida!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(this,"Permissão Negada!", Toast.LENGTH_SHORT).show();

                    }
                }
                return;
            }
        }
    }
}

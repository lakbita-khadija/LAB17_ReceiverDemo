package com.example.receiverdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private AirplaneModeReceiver airplaneReceiver;
    private boolean isReceiverRegistered = false;

    private TextView tvStatus;
    private Button btnToggleAirplane;
    private Button btnSendCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        airplaneReceiver = new AirplaneModeReceiver();

        tvStatus = findViewById(R.id.tvStatus);
        btnToggleAirplane = findViewById(R.id.btnToggleAirplane);
        btnSendCustom = findViewById(R.id.btnSendCustom);

        btnToggleAirplane.setOnClickListener(v -> toggleAirplaneReceiver());

        btnSendCustom.setOnClickListener(v -> sendCustomBroadcast());
    }

    private void toggleAirplaneReceiver() {

        if (!isReceiverRegistered) {

            IntentFilter filter =
                    new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);

            ContextCompat.registerReceiver(
                    this,
                    airplaneReceiver,
                    filter,
                    ContextCompat.RECEIVER_EXPORTED
            );

            isReceiverRegistered = true;

            tvStatus.setText("Receiver avion ACTIVÉ");

            btnToggleAirplane.setText("Désactiver Receiver");

        } else {

            unregisterReceiver(airplaneReceiver);

            isReceiverRegistered = false;

            tvStatus.setText("Receiver avion DÉSACTIVÉ");

            btnToggleAirplane.setText("Activer Receiver");
        }
    }

    private void sendCustomBroadcast() {

        Intent intent =
                new Intent("com.example.receiverdemo.CUSTOM_EVENT");

        intent.setPackage(getPackageName());

        intent.putExtra(
                "message",
                "Bonjour Khadija ! Broadcast personnalisé reçu."
        );

        sendBroadcast(intent);

        Toast.makeText(
                this,
                "Broadcast envoyé",
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onDestroy() {

        if (isReceiverRegistered) {
            unregisterReceiver(airplaneReceiver);
        }

        super.onDestroy();
    }
}
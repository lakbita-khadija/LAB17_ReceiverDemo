package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AirplaneModeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            boolean isAirplaneOn = intent.getBooleanExtra("state", false);

            String message = isAirplaneOn
                    ? "Mode avion activé"
                    : "Mode avion désactivé";

            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }
}
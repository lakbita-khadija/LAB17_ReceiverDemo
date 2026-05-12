package com.example.receiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomEventReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.receiverdemo.CUSTOM_EVENT".equals(intent.getAction())) {
            String message = intent.getStringExtra("message");
            Toast.makeText(context, "Message reçu : " + message, Toast.LENGTH_LONG).show();
        }
    }
}
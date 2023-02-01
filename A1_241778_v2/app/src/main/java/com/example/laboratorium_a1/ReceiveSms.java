package com.example.laboratorium_a1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiveSms extends BroadcastReceiver {

    //class variables
    private int duration = Toast.LENGTH_SHORT;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "SMS", duration).show();
    }
}

package com.example.admin.baothuc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Admin on 9/14/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    public static final String STATUS = "status";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("ALo", "Toi gio roi");
        String status = intent.getExtras().getString(MainActivity.STATUS);
        Intent iIntent = new Intent(context, Music.class);
        iIntent.putExtra(STATUS, status);
        context.startService(iIntent);
    }
}

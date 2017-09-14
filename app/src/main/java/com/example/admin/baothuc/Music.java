package com.example.admin.baothuc;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by Admin on 9/14/2017.
 */

public class Music extends Service {

    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String status = intent.getExtras().getString(AlarmReceiver.STATUS);
        if(status.equals("on")){
            mediaPlayer = MediaPlayer.create(this, R.raw.emgaimua);
            mediaPlayer.start();
            status = "off";
        }else{
            mediaPlayer.stop();
            mediaPlayer.reset();
        }


        return START_NOT_STICKY;
    }
}

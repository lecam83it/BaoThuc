package com.example.admin.baothuc;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String STATUS = "status";

    private TimePicker timePicker;
    private Button btnDel, btnOk;
    private TextView txtGioHen;
    private Calendar calendar;
    private AlarmManager manager;
    private PendingIntent pendingIntent;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = (TimePicker) findViewById(R.id.timepicker);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnDel = (Button) findViewById(R.id.btnDel);
        txtGioHen = (TextView) findViewById(R.id.txtGioHen);
        calendar = Calendar.getInstance();

        manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        intent = new Intent(MainActivity.this, AlarmReceiver.class);

        btnOk.setOnClickListener(this);
        btnDel.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btnOk:

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                String s = hour + " : " + minute;
                intent.putExtra(STATUS, "on");
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0 , intent, PendingIntent.FLAG_UPDATE_CURRENT);
                manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                txtGioHen.setText("Gio Ban Hen La: " + s);
                break;
            case R.id.btnDel:
                txtGioHen.setText("");
                manager.cancel(pendingIntent);
                intent.putExtra(STATUS, "off");
                sendBroadcast(intent);
                break;
        }
    }
}

package com.jl.servicedemo;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import static android.app.Service.START_STICKY;
import static android.content.Context.ALARM_SERVICE;

/**
 * 服务demo
 */
public class ServiceDemo extends Service {

    AlarmManager mAlarmManager = null;
    PendingIntent mPendingIntent = null;

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Callback Successed!", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }
}
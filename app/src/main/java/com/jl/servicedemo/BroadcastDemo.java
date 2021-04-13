package com.jl.servicedemo;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * 广播demo
 */
public class BroadcastDemo extends BroadcastReceiver {
    private Notification notification;
    private NotificationManager manager;
    private final static int NOTIFY_ID = 100;
    private String TAG = "BroadcastDemo";

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        showNotification(context);
    }

    private void showNotification(Context context) {
        manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent hangIntent = new Intent(context, MainActivity.class);
        PendingIntent hangPendingIntent = PendingIntent.getActivity(context, 1001, hangIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        String CHANNEL_ID = "通知";//应用频道Id唯一值， 长度若太长可能会被截断，
        String CHANNEL_NAME = "测试demo";//最长40个字符，太长会被截断
        notification = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentTitle("创美科技")
                .setContentText("测试内容")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(hangPendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .build();

        //Android 8.0 以上需包添加渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(notificationChannel);
        }

        manager.notify(NOTIFY_ID, notification);
    }
}

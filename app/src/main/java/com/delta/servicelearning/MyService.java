package com.delta.servicelearning;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Shufeng.Wu on 2017/9/4.
 */

public class MyService extends Service {

    private final String TAG = MyService.class.getSimpleName();
    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " + Thread.currentThread().getId());
        /*NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(this);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivities(this, 0, new Intent[]{notificationIntent}, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentTitle("这是通知的标题")
                .setContentText("这是通知的内容")
                .setContentIntent(contentIntent);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }
        manager.notify(1, notification);*/
        //startForeground(1,notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onStartCommand run: " + Thread.currentThread().getId());
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    class MyBinder extends Binder {
        public void startDownLoad() {
            Log.i(TAG, "startDownLoad: ");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "startDownLoad run: " + Thread.currentThread().getId());
                }
            }).start();
        }
    }
}

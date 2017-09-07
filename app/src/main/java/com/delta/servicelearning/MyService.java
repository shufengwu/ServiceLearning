package com.delta.servicelearning;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Shufeng.Wu on 2017/9/4.
 */

public class MyService extends Service {

    private final String TAG = MyService.class.getSimpleName();
    private MyBinder myBinder = new MyBinder();
    private String noti = "ceshi";
    MyAidlService.Stub mBinder = new MyAidlService.Stub() {
        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public String toUpperCase(String str) throws RemoteException {
            if (str != null) {
                return str.toUpperCase() + noti;
            }
            return null;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: " + Thread.currentThread().getId());
        Log.i(TAG, "onCreate: " + Process.myPid());
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

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
        /*new Thread(
                new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "onStartCommand run: " + Thread.currentThread().getId());
            }
        }).start();*/
        Notification.Builder builder = new Notification.Builder(this.getApplicationContext());
        Intent intentNofification = new Intent(this, MainActivity.class);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, intentNofification, 0))
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setContentTitle("通知标题")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("通知内容:后台service正在运行")
                .setWhen(System.currentTimeMillis());
        Notification notification = builder.build();
        notification.defaults = Notification.DEFAULT_SOUND;
        startForeground(110, notification);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知
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

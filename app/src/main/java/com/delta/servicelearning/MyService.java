package com.delta.servicelearning;

import android.app.Service;
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
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
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
        }
    }
}

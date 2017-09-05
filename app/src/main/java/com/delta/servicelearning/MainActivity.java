package com.delta.servicelearning;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private Button btnStart;
    private Button btnStop;
    private Button btnBind;
    private Button btnUnbind;
    private MyService.MyBinder myBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
            myBinder = (MyService.MyBinder) service;
            myBinder.startDownLoad();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: "+Thread.currentThread().getId());
        btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);
        btnStop = (Button) findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(this);
        btnBind = (Button) findViewById(R.id.btn_bind);
        btnBind.setOnClickListener(this);
        btnUnbind = (Button) findViewById(R.id.btn_unbind);
        btnUnbind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Intent startIntent = new Intent(MainActivity.this, MyService.class);
                startService(startIntent);
                break;
            case R.id.btn_stop:
                Intent stopIntent = new Intent(MainActivity.this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.btn_bind:
                Intent bindIntent = new Intent(MainActivity.this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                Intent unbindIntent = new Intent(MainActivity.this, MyService.class);
                if (myBinder != null && myBinder.isBinderAlive()) {
                    if (isServiceRunning(this, MyService.class.getCanonicalName())) {
                        unbindService(connection);
                    }

                }

                break;
            default:
                break;
        }

    }

    public static boolean isServiceRunning(Context mContext, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(30);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className) == true) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}

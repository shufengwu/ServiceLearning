package com.delta.intentservicetest;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Shufeng.Wu on 2017/9/7.
 */

public class UploadImgService extends IntentService {

    public static final String ACTION_NAME = "com.delta.intentservicetest.UploadImgService";
    public static final String EXTRA_IMG_PATH = "com.delta.intentservicetest.IMG_PATH";
    public final String TAG = UploadImgService.class.getSimpleName();


    public UploadImgService() {
        super("UploadImgService");
    }

    public static void startUploadImg(Context context, String path) {
        Intent intent = new Intent(context, UploadImgService.class);
        intent.setAction(ACTION_NAME);
        intent.putExtra(EXTRA_IMG_PATH, path);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        Log.i(TAG, "onCreate: id " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        Log.i(TAG, "onDestroy: id" + Thread.currentThread().getId());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            if (intent.getAction().equals(ACTION_NAME)) {
                final String path = intent.getStringExtra(EXTRA_IMG_PATH);
                handleUploadImg(path);
            }
        }
    }

    private void handleUploadImg(String path) {
        try {
            Thread.sleep(10000);

            //下载完成发送广播
            Intent intent = new Intent(MainActivity.UPLOAD_RESULT);
            intent.putExtra(EXTRA_IMG_PATH, path);
            sendBroadcast(intent);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

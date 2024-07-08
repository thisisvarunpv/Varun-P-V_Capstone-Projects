package com.example.powermanagementandbackgroundservices;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
public class MyService extends Service {
    private static final String TAG = "MyService";
    private final IBinder binder = new LocalBinder();
    private boolean isRunning = false;
    public class LocalBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service created");
        isRunning = true;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service started");
        // Your service logic here
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service destroyed");
        isRunning = false;
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    public boolean isRunning() {
        return isRunning;
    }
}


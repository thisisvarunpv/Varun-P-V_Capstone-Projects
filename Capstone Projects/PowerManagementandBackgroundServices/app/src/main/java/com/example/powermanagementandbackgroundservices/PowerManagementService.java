package com.example.powermanagementandbackgroundservices;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
public class PowerManagementService extends Service {
    private final IBinder binder = new LocalBinder();
    private boolean isRunning = false;
    public class LocalBinder extends Binder {
        PowerManagementService getService() {
            return PowerManagementService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        // Add your power management logic here
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        // Cleanup logic if needed
    }
    public boolean isRunning() {
        return isRunning;
    }
}

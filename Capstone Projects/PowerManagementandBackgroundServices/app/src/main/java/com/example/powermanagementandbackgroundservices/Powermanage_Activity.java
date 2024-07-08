package com.example.powermanagementandbackgroundservices;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class Powermanage_Activity extends AppCompatActivity {
    private static final String TAG = "Powermanage_Activity";
    private Button startServiceButton;
    private Button stopServiceButton;
    private TextView statusTextView;
    private Button navigateToBackgroundServiceButton;
    private PowerManagementService powerManagementService;
    private boolean isServiceBound;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            PowerManagementService.LocalBinder binder = (PowerManagementService.LocalBinder) service;
            powerManagementService = binder.getService();
            isServiceBound = true;
            updateServiceStatus();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_powermanage);

        startServiceButton = findViewById(R.id.start_service_button);
        stopServiceButton = findViewById(R.id.stop_service_button);
        statusTextView = findViewById(R.id.status_text_view);
        navigateToBackgroundServiceButton = findViewById(R.id.navigate_to_background_service_button);

        startServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPowerManagementService();
            }
        });

        stopServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPowerManagementService();
            }
        });

        navigateToBackgroundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Powermanage_Activity.this, BackgroundService_Activity.class);
                startActivity(intent);
            }
        });
    }

    private void startPowerManagementService() {
        Intent serviceIntent = new Intent(this, PowerManagementService.class);
        startService(serviceIntent);
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        statusTextView.setText("Power Management Active\n\nBattery usage minimized by turning off non-essential services");
        Toast.makeText(this, "Power Management Service started", Toast.LENGTH_SHORT).show();
    }

    private void stopPowerManagementService() {
        if (isServiceBound) {
            unbindService(serviceConnection);
            isServiceBound = false;
        }
        Intent serviceIntent = new Intent(this, PowerManagementService.class);
        stopService(serviceIntent);
        statusTextView.setText("Power Management Service stopped");
        Toast.makeText(this, "Power Management Service stopped", Toast.LENGTH_SHORT).show();
    }

    private void updateServiceStatus() {
        if (isServiceBound && powerManagementService != null && powerManagementService.isRunning()) {
            statusTextView.setText("Power Management Active\n\nBattery usage minimized by turning off non-essential services");
        } else {
            statusTextView.setText("Power Management Service stopped");
        }
    }
}

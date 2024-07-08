package com.example.powermanagementandbackgroundservices;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {
    private Button powerManagementButton;
    private Button backgroundServiceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        powerManagementButton = findViewById(R.id.power_management_button);
        backgroundServiceButton = findViewById(R.id.background_service_button);
        powerManagementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Powermanage_Activity.class);
                startActivity(intent);
            }
        });
        backgroundServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BackgroundService_Activity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.ServicesExample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ServicesExample.Setvices.ExampleBackgroundServices;
import com.example.ServicesExample.Setvices.ExampleForegroundService;

public class MainActivity extends AppCompatActivity {

    private EditText foregroundInput_et, backgroundInput_et;
    public static String INPUT_EXTRA = "INPUT_EXTRA";
    private Button startForeground_btn, stopForeground_btn;
    private Button startBackground_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObjects();
        initListeners();
    }


    private void initObjects() {
        foregroundInput_et = findViewById(R.id.foreground_ET_input);
        backgroundInput_et = findViewById(R.id.background_ET_input);

        startForeground_btn = findViewById(R.id.foreground_Btn_startService);
        stopForeground_btn = findViewById(R.id.foreground_Btn_stopService);

        startBackground_btn = findViewById(R.id.background_Btn_startService);
    }

    private void initListeners() {
        startForeground_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startForegroundService();
            }
        });

        stopForeground_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopForegroundService();
            }
        });

        startBackground_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBackgroundService();
            }
        });
    }

    private void startForegroundService() {
        String input = foregroundInput_et.getText().toString();
        Intent serviceIntent = new Intent(this, ExampleForegroundService.class);
        serviceIntent.putExtra(INPUT_EXTRA, input);
        startService(serviceIntent);
    }

    private void stopForegroundService() {
        Intent intent = new Intent(this, ExampleForegroundService.class);
        stopService(intent);
    }

    private void startBackgroundService() {
        String input = backgroundInput_et.getText().toString();

        Intent serviceIntent = new Intent(this, ExampleBackgroundServices.class);
        serviceIntent.putExtra(INPUT_EXTRA, input);

        ContextCompat.startForegroundService(this, serviceIntent);
    }
}
package com.example.ServicesExample.Setvices;

import android.app.IntentService;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.example.ServicesExample.MainActivity;
import com.example.ServicesExample.R;
import static com.example.ServicesExample.App.CHANNEL_ID;

public class ExampleBackgroundServices extends IntentService {

    private String TAG = "ExampleBackgroundServices";
    private PowerManager.WakeLock wakeLock;

    public ExampleBackgroundServices() {
        super("ExampleBackgroundServices");
        setIntentRedelivery(true); //Service will restart soon as possible
    }

    @Override
    public void onCreate() {
        super.onCreate();

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "ExampleApp:WakeLock");
        wakeLock.acquire();

        Log.d(TAG, "WakeLock acquire");
        
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Example intent service")
                    .setContentText("Running...")
                    .setSmallIcon(R.drawable.ic_service)
                    .build();
            
            //startForeground(1, notification);

        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(TAG, "onHandleIntent called");

        String input = intent.getStringExtra(MainActivity.INPUT_EXTRA);
        for (int i = 0; i < 10; i += 1) {
            Log.d(TAG, input + "- " + i);
            SystemClock.sleep(1000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        
        wakeLock.release();
        Log.d(TAG, "onDestroy: wakelock released");

    }
}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingConfig;
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingFragment;

public class LiveActivity extends AppCompatActivity {

    String userID, name, liveID;
    boolean isHost;

    TextView liveIdText;
    ImageView shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        liveIdText = findViewById(R.id.live_id_textview);
        shareBtn = findViewById(R.id.share_btn);

        userID = getIntent().getStringExtra("user_id");
        name = getIntent().getStringExtra("name");
        liveID = getIntent().getStringExtra("live_id");
        isHost = getIntent().getBooleanExtra("host", false);

        Log.d("ZEGO_DEBUG", "userID = " + userID);
        Log.d("ZEGO_DEBUG", "name = " + name);
        Log.d("ZEGO_DEBUG", "liveID = " + liveID);
        Log.d("ZEGO_DEBUG", "isHost = " + isHost);

        liveIdText.setText("LIVE ID : " + liveID);

        shareBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,
                    "Join my live\nLive ID : " + liveID);

            startActivity(Intent.createChooser(intent, "Share via"));
        });

        checkPermissions();
    }

    private void checkPermissions() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ||

                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.RECORD_AUDIO)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO
                    },
                    100);

        } else {

            addFragment();
        }
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {

        super.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults);

        if (requestCode == 100) {

            addFragment();
        }
    }

    private void addFragment() {

        try {

            Log.d("ZEGO_DEBUG", "addFragment started");

            ZegoUIKitPrebuiltLiveStreamingConfig config;

            if (isHost) {
                config = ZegoUIKitPrebuiltLiveStreamingConfig.host();
            } else {
                config = ZegoUIKitPrebuiltLiveStreamingConfig.audience();
            }

            Log.d("ZEGO_DEBUG", "Config created");

            ZegoUIKitPrebuiltLiveStreamingFragment fragment =
                    ZegoUIKitPrebuiltLiveStreamingFragment.newInstance(
                            AppConstants.appId,
                            AppConstants.appSign,
                            userID,
                            name,
                            liveID,
                            config
                    );

            Log.d("ZEGO_DEBUG", "Fragment created");

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

            Log.d("ZEGO_DEBUG", "Fragment added");

            Toast.makeText(this,
                    "ZEGO Loaded",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {

            Log.e("ZEGO_ERROR",
                    "ERROR = " + e.getMessage(),
                    e);

            Toast.makeText(this,
                    "ERROR : " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
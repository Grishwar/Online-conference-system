package com.example.myapplication;

// NotificationReceiver.java
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String meetingTitle = intent.getStringExtra("MEETING_TITLE");
        String notificationMessage = "Meeting '" + meetingTitle + "' is starting in 30 minutes!";
        Toast.makeText(context, notificationMessage, Toast.LENGTH_LONG).show();
    }
}



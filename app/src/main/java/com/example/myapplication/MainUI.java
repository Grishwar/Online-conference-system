package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ui);
        ImageView ivNewMeeting = findViewById(R.id.iv_new_meeting);
        ImageView ivJoin = findViewById(R.id.iv_join);
        ImageView ivSchedule = findViewById(R.id.iv_schedule);
        ImageView ivNewTerm = findViewById(R.id.iv_new_term);

        ImageView ivSetting = findViewById(R.id.iv_setting);


        // Set OnClickListener for iv_new_meeting ImageView
        ivNewMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to a new activity or perform desired action
                Intent intent = new Intent(MainUI.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // Set OnClickListener for iv_join ImageView
        ivJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to a new activity or perform desired action
                Intent intent = new Intent(MainUI.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for iv_schedule ImageView
        ivSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to a new activity or perform desired action
                Intent intent = new Intent(MainUI.this, MainActivityToDoList.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for iv_new_term ImageView
        ivNewTerm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to a new activity or perform desired action
                Intent intent = new Intent(MainUI.this, TermsAndCondition.class);
                startActivity(intent);
            }
        });



        // Set OnClickListener for iv_setting ImageView
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to a new activity or perform desired action
                Intent intent = new Intent(MainUI.this, settings.class);
                startActivity(intent);
            }
        });
    }
}
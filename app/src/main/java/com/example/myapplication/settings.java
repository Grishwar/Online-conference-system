package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Button viewProfileButton = findViewById(R.id.btn_view_profile);
        Button termsAndConditionsButton = findViewById(R.id.btn_terms_and_conditions);
        Button moreSettingsButton = findViewById(R.id.btn_more_settings);
        Button helpAndFeedbackButton = findViewById(R.id.btn_help_and_feedback);
        Button logoutButton = findViewById(R.id.btn_logout);


        // Set click listeners for each button
        viewProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to ViewProfileActivity
                startActivity(new Intent(settings.this, ViewProfile.class));
            }
        });

        termsAndConditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to TermsAndConditionsActivity
                startActivity(new Intent(settings.this, TermsAndCondition.class));
            }
        });

        moreSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to MoreSettingsActivity
                startActivity(new Intent(settings.this, settings.class));
            }
        });

        helpAndFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to HelpAndFeedbackActivity
                startActivity(new Intent(settings.this, Help_Feedback.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to LoginActivity or any other appropriate activity
                startActivity(new Intent(settings.this, Login.class));
                finish(); // Close this activity
            }
        });
    }
}
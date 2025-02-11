package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Help_Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_feedback);
        Button feedbackButton = findViewById(R.id.btn_feedback);

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the feedback link (replace "YOUR_FEEDBACK_LINK" with your actual feedback link)
                String feedbackLink = "https://docs.google.com/forms/d/e/1FAIpQLSdiZAU1fkTdMxMO4lM1P66XHD6sNYAEGN4cm-5aJ6io6ilLLQ/viewform?usp=sf_link";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(feedbackLink));
                startActivity(intent);
            }
        });
    }
}
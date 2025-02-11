package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class selection_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_page);
    }


    public void onStudentButtonClick(View view) {
        // Handle Student button click
        Intent intent = new Intent(this, MainUI.class);
        startActivity(intent);
    }

    public void onTeacherButtonClick(View view) {
        // Handle Teacher button click
        Intent intent = new Intent(this, MainUI.class);
        startActivity(intent);
    }
}

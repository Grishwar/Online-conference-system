package com.example.myapplication;

// AddMeetingActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class AddMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        final EditText titleEditText = findViewById(R.id.titleEditText);
        final DatePicker datePicker = findViewById(R.id.datePicker);
        final TimePicker timePicker = findViewById(R.id.timePicker);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titleEditText.getText().toString();

                // Get selected date and time
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute);

                Date dateTime = calendar.getTime();

                Meeting meeting = new Meeting(title, dateTime);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("meeting", meeting);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}

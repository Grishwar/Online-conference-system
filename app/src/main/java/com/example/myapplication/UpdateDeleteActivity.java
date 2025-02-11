package com.example.myapplication;

// UpdateDeleteActivity.java
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

public class UpdateDeleteActivity extends AppCompatActivity {

    private EditText titleEditText;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private Meeting meeting;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        titleEditText = findViewById(R.id.titleEditText);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);

        Button updateButton = findViewById(R.id.updateButton);
        Button deleteButton = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        if (intent.hasExtra("meeting")) {
            meeting = (Meeting) intent.getSerializableExtra("meeting");
            position = intent.getIntExtra("position", -1);

            if (meeting != null && position != -1) {
                titleEditText.setText(meeting.getTitle());

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(meeting.getDateTime());

                datePicker.updateDate(calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
                timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));
            }
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMeeting();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMeeting();
            }
        });
    }

    private void updateMeeting() {
        if (meeting != null && position != -1) {
            String title = titleEditText.getText().toString();

            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();

            int hour = timePicker.getCurrentHour();
            int minute = timePicker.getCurrentMinute();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day, hour, minute);

            Date dateTime = calendar.getTime();

            Meeting updatedMeeting = new Meeting(title, dateTime);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("meeting", updatedMeeting);
            resultIntent.putExtra("position", position);

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    private void deleteMeeting() {
        if (position != -1) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("position", position);

            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}

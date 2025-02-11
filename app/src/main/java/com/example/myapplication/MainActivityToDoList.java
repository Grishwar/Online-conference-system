package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;


public class MainActivityToDoList extends AppCompatActivity {

    private static final String MEETINGS_PREFS = "MeetingsPrefs";
    private static final int ADD_MEETING_REQUEST = 1;
    private static final int VIEW_ALL_MEETINGS_REQUEST = 2;

    private ArrayList<Meeting> meetings;
    private ArrayAdapter<Meeting> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_to_do_list);

        meetings = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, meetings);

        ListView listViewMeetings = findViewById(R.id.listViewMeetings);
        listViewMeetings.setAdapter(adapter);

        listViewMeetings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                openUpdateDeleteActivity(position);
            }
        });

        sharedPreferences = getSharedPreferences(MEETINGS_PREFS, MODE_PRIVATE);

        loadMeetings();

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddMeetingActivity();
            }
        });

        Button viewAllButton = findViewById(R.id.viewAllButton);
        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewAllMeetingsActivity();
            }
        });
    }

    private void openAddMeetingActivity() {
        Intent intent = new Intent(this, AddMeetingActivity.class);
        startActivityForResult(intent, ADD_MEETING_REQUEST);
    }

    private void openUpdateDeleteActivity(int position) {
        Intent intent = new Intent(this, UpdateDeleteActivity.class);
        intent.putExtra("meeting", meetings.get(position));
        intent.putExtra("position", position);
        startActivityForResult(intent, ADD_MEETING_REQUEST);
    }

    private void openViewAllMeetingsActivity() {
        Intent intent = new Intent(this, ViewAllMeetingsActivity.class);
        startActivityForResult(intent, VIEW_ALL_MEETINGS_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_MEETING_REQUEST:
                    Meeting newMeeting = (Meeting) data.getSerializableExtra("meeting");
                    int position = data.getIntExtra("position", -1);

                    if (position == -1) {
                        // Add new meeting
                        meetings.add(newMeeting);
                    } else {
                        // Update existing meeting
                        meetings.set(position, newMeeting);
                    }

                    saveMeetings();
                    adapter.notifyDataSetChanged();
                    break;

                case VIEW_ALL_MEETINGS_REQUEST:
                    // Handle any result from the ViewAllMeetingsActivity
                    break;
            }
        }
    }

    private void loadMeetings() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("meetings", "");
        Type type = new TypeToken<ArrayList<Meeting>>(){}.getType();
        meetings = gson.fromJson(json, type);

        if (meetings == null) {
            meetings = new ArrayList<>();
        }

        adapter.addAll(meetings);
        adapter.notifyDataSetChanged();
    }

    private void saveMeetings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(meetings);
        editor.putString("meetings", json);
        editor.apply();
    }
}
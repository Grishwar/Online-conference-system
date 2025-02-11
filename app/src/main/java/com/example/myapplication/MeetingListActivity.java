package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MeetingListActivity extends AppCompatActivity {
    private ListView listViewAllMeetings;
    private ArrayList<Meeting> allMeetings;
    private ArrayAdapter<Meeting> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_list);

        // Initialize UI elements
        listViewAllMeetings = findViewById(R.id.listViewAllMeetings);

        // Initialize meeting list and adapter
        allMeetings = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allMeetings);
        listViewAllMeetings.setAdapter(adapter);

        // Load all meetings from SharedPreferences
        loadAllMeetings();
    }

    // Function to load all meetings from SharedPreferences
    private void loadAllMeetings() {
        // Implement loading all meetings logic here
        // Retrieve the meetings from SharedPreferences or any other local storage

        // For demonstration, assume we have a shared instance of SharedPreferences
        // and load the meetings directly from there
        // Note: You may need to adjust this based on your actual storage mechanism
        SharedPreferences sharedPreferences = getSharedPreferences("MeetingPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("meetingList", "");
        Type type = new TypeToken<List<Meeting>>() {}.getType();
        ArrayList<Meeting> savedMeetings = gson.fromJson(json, type);

        if (savedMeetings != null) {
            allMeetings.addAll(savedMeetings);
            adapter.notifyDataSetChanged();
        }
    }
}
package com.example.myapplication;

// ViewAllMeetingsActivity.java
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ViewAllMeetingsActivity extends AppCompatActivity {

    private ListView listViewAllMeetings;
    private ArrayList<Meeting> allMeetings;
    private ArrayAdapter<Meeting> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_meetings);

        listViewAllMeetings = findViewById(R.id.listViewAllMeetings);

        allMeetings = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allMeetings);
        listViewAllMeetings.setAdapter(adapter);

        loadAllMeetings();
    }

    private void loadAllMeetings() {
        // Load meetings from SharedPreferences or any other local storage
        // ...

        // For demonstration, let's assume meetings are loaded from SharedPreferences
        // Adjust this based on your actual storage mechanism

        SharedPreferences sharedPreferences = getSharedPreferences("MeetingsPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("meetings", "");
        Type type = new TypeToken<ArrayList<Meeting>>(){}.getType();
        ArrayList<Meeting> storedMeetings = gson.fromJson(json, type);

        if (storedMeetings != null) {
            allMeetings.addAll(storedMeetings);
            adapter.notifyDataSetChanged();
        }
    }
}

package com.example.myapplication;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    Button goLiveBtn;
    TextInputEditText liveIdInput,nameInput;
    String liveID;
    String name,userID;

    SharedPreferences sharedPreferences;

    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("name_pref",MODE_PRIVATE);


        materialToolbar=findViewById(R.id.materialToolbar);
        frameLayout=findViewById(R.id.frameLayout);
        goLiveBtn = findViewById(R.id.go_live_btn);
        liveIdInput = findViewById(R.id.live_id_input);
        nameInput = findViewById(R.id.name_input);

        nameInput.setText(sharedPreferences.getString("name",""));

        liveIdInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                liveID = liveIdInput.getText().toString();
                if(liveID.length()==0){
                    goLiveBtn.setText("start new live");
                }else{
                    goLiveBtn.setText("join a live");
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        goLiveBtn.setOnClickListener((v)->{
            name = nameInput.getText().toString();

            if (name.isEmpty()){
                nameInput.setError("Name is required");
                nameInput.requestFocus();
                return;
            }

            liveID = liveIdInput.getText().toString();

            if(liveID.length()>0 && liveID.length()!=5){
                liveIdInput.setError("invalid live id");
                liveIdInput.requestFocus();
                return;
            }

            startMeeting();
        });
    }

    void startMeeting(){
        sharedPreferences.edit().putString("name",name).apply();
        Log.i("LOG","start meeting");

        boolean isHost = true;
        if(liveID.length()==5)
            isHost=false;
        else
            liveID = generateLiveID();

        userID = UUID.randomUUID().toString();

        Intent intent = new Intent(MainActivity.this,LiveActivity.class);
        intent.putExtra("user_id",userID);
        intent.putExtra("name",name);
        intent.putExtra("live_id",liveID);
        intent.putExtra("host",isHost);
        startActivity(intent);

    }

    String generateLiveID(){
        StringBuilder id = new StringBuilder();
        while (id.length()!=5){
            int random = new Random().nextInt(10);
            id.append(random);
        }
        return id.toString();
    }
}
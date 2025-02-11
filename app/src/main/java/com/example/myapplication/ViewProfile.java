package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

public class ViewProfile extends AppCompatActivity {

    private EditText etName;
    private EditText etEmail;
    private EditText etId;
    private ImageView ivProfilePicture;
    private DatePicker dpDateOfBirth;
    private SharedPreferences sharedPreferences;
    private static final int REQUEST_CODE_IMAGE_PICKER = 1;  // Request code for image picker intent



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etId = findViewById(R.id.et_id);
        ivProfilePicture = findViewById(R.id.iv_profile_picture);
        dpDateOfBirth = findViewById(R.id.dp_date_of_birth);
        sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);

        // Pre-fill data from SharedPreferences (optional)
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String id = sharedPreferences.getString("id", "");
        int birthYear = sharedPreferences.getInt("birth_year", 0);
        int birthMonth = sharedPreferences.getInt("birth_month", 0);
        int birthDay = sharedPreferences.getInt("birth_day", 0);
        etName.setText(name);
        etEmail.setText(email);
        etId.setText(id);
        dpDateOfBirth.updateDate(birthYear, birthMonth, birthDay);  // Set pre-saved date (if any)


        // Handle profile picture selection
        ivProfilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");  // Allow selecting any image type
                startActivityForResult(intent, REQUEST_CODE_IMAGE_PICKER);
            }
        });

        // Handle date of birth selection
        dpDateOfBirth.init(birthYear, birthMonth, birthDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Update any display or store the selected date (already handled in onCreate)
            }
        });

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String id = etId.getText().toString().trim();
                String profilePicturePath = null;  // Initialize profile picture path

                // Basic data validation (optional)
                if (name.isEmpty() || email.isEmpty() || id.isEmpty()) {
                    // Show error message
                    return;
                }

                // Handle profile picture (if selected)
                // This part was incorrect in the original code, it should be handled in onActivityResult
                // I've removed it from here and added it to onActivityResult method

                // Get date of birth from DatePicker (already handled in onCreate)

                // Save data to SharedPreferences
                sharedPreferences.edit()
                        .putString("name", name)
                        .putString("email", email)
                        .putString("id", id)
                        .putString("profile_picture", profilePicturePath)  // Save profile picture path (if any)
                        .putInt("birth_year", dpDateOfBirth.getYear())
                        .putInt("birth_month", dpDateOfBirth.getMonth())
                        .putInt("birth_day", dpDateOfBirth.getDayOfMonth())
                        .apply();

                // Handle successful save (e.g., navigate to another activity)
                finish(); // Close this activity
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_IMAGE_PICKER && resultCode == RESULT_OK) {
            if (data != null) {
                // Handle the selected image
                Uri selectedImageUri = data.getData();
                // You can use Glide or Picasso libraries to load and display the image
                ivProfilePicture.setImageURI(selectedImageUri);
            }
        }
    }
}

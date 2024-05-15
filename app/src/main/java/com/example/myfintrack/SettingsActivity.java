package com.example.myfintrack;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize UI components
        ImageView backButton = findViewById(R.id.backButton);

        // Set up back button to return to the home screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to the previous activity
            }
        });

        // Handle clicks on settings options
        findViewById(R.id.profileSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle profile settings click
                // Example: startActivity(new Intent(SettingsActivity.this, ProfileSettingsActivity.class));
            }
        });

        findViewById(R.id.notificationPreferences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle notification preferences click
                // Example: startActivity(new Intent(SettingsActivity.this, NotificationPreferencesActivity.class));
            }
        });

        findViewById(R.id.dataSyncOptions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle data sync options click
                // Example: startActivity(new Intent(SettingsActivity.this, DataSyncOptionsActivity.class));
            }
        });
    }
}

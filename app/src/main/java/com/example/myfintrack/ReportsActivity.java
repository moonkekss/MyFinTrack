package com.example.myfintrack;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class ReportsActivity extends AppCompatActivity {

    private Spinner reportTypeDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        // Initialize UI components
        ImageView backButton = findViewById(R.id.backButton);
        reportTypeDropdown = findViewById(R.id.reportTypeDropdown);

        // Set up back button to return to the home screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to the previous activity
            }
        });

        // Set up report type dropdown
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.report_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reportTypeDropdown.setAdapter(adapter);
        reportTypeDropdown.setPrompt("Select Report Type");
    }
}

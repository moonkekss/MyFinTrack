package com.example.myfintrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class BudgetActivity extends AppCompatActivity {

    private Button setBudgetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        // Initialize UI components
        ImageView backButton = findViewById(R.id.backButton);
        setBudgetButton = findViewById(R.id.setBudgetButton);

        // Set up back button to return to the home screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to the previous activity
            }
        });

        // Set up set budget button click listener
        setBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle setting up a budget
                // For now, simply display a placeholder action
                // Example: startActivity(new Intent(BudgetActivity.this, SetBudgetActivity.class));
            }
        });
    }
}

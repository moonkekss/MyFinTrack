package com.example.myfintrack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.Executors;

public class AddTransactionActivity extends AppCompatActivity {

    private EditText inputDescription, inputAmount, inputDate, inputNotes;
    private Spinner inputCategory;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Initialize UI components
        ImageView backButton = findViewById(R.id.backButton);
        inputDescription = findViewById(R.id.inputDescription);
        inputAmount = findViewById(R.id.inputAmount);
        inputDate = findViewById(R.id.inputDate);
        inputCategory = findViewById(R.id.inputCategory);
        inputNotes = findViewById(R.id.inputNotes);
        saveButton = findViewById(R.id.saveButton);

        // Set up back button to return to the home screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to the previous activity
            }
        });

        // Set up category spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.transaction_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputCategory.setAdapter(adapter);
        inputCategory.setPrompt("Category");

        // Set up save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTransaction();
            }
        });
    }

    private void saveTransaction() {
        String description = inputDescription.getText().toString();
        String amountStr = inputAmount.getText().toString();
        if (amountStr.isEmpty()) {
            inputAmount.setError("Amount is required");
            inputAmount.requestFocus();
            return;
        }
        double amount = Double.parseDouble(amountStr);
        String date = inputDate.getText().toString();
        String category = inputCategory.getSelectedItem().toString();
        String notes = inputNotes.getText().toString();

        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setDate(date);
        transaction.setCategory(category);
        transaction.setNotes(notes);

        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getApplicationContext()).transactionDao().insert(transaction);
                Log.d("AddTransactionActivity", "Transaction saved: " + transaction);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                });
            }
        });
    }

}

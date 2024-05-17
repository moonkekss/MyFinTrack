package com.example.myfintrack;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.Executors;

public class EditTransactionActivity extends AppCompatActivity {

    private EditText inputDescription, inputAmount, inputDate, inputNotes;
    private Spinner inputCategory;
    private Button saveButton;
    private Transaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_transaction);

        // Initialize UI components
        inputDescription = findViewById(R.id.inputDescription);
        inputAmount = findViewById(R.id.inputAmount);
        inputDate = findViewById(R.id.inputDate);
        inputCategory = findViewById(R.id.inputCategory);
        inputNotes = findViewById(R.id.inputNotes);
        saveButton = findViewById(R.id.saveButton);

        initializeSpinner(); // Initialize the spinner with categories

        // Get transaction ID from intent and load the transaction
        int transactionId = getIntent().getIntExtra("transaction_id", -1);
        if (transactionId != -1) {
            loadTransaction(transactionId);
        }

        // Set up save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTransaction();
            }
        });
    }

    private void initializeSpinner() {
        // Assuming you have an array of categories
        String[] categories = {"Food", "Travel", "Shopping", "Bills"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputCategory.setAdapter(adapter);
    }

    private void loadTransaction(int transactionId) {
        Executors.newSingleThreadExecutor().execute(() -> {
            transaction = AppDatabase.getInstance(getApplicationContext()).transactionDao().getTransactionById(transactionId);
            runOnUiThread(() -> {
                if (transaction != null) {
                    inputDescription.setText(transaction.getDescription());
                    inputAmount.setText(String.valueOf(transaction.getAmount()));
                    inputDate.setText(transaction.getDate());
                    // Assuming inputCategory has been set up with categories
                    int categoryPosition = ((ArrayAdapter<String>) inputCategory.getAdapter()).getPosition(transaction.getCategory());
                    inputCategory.setSelection(categoryPosition);
                    inputNotes.setText(transaction.getNotes());
                }
            });
        });
    }

    private void updateTransaction() {
        try {
            String description = inputDescription.getText().toString();
            String amountStr = inputAmount.getText().toString();
            String date = inputDate.getText().toString();
            String category = inputCategory.getSelectedItem().toString();
            String notes = inputNotes.getText().toString();

            if (description.isEmpty() || amountStr.isEmpty() || date.isEmpty() || category.isEmpty()) {
                // Show error message to the user if any field is empty
                return;
            }

            if (transaction != null) {
                transaction.setDescription(description);
                transaction.setAmount(Double.parseDouble(amountStr));
                transaction.setDate(date);
                transaction.setCategory(category);
                transaction.setNotes(notes);

                Executors.newSingleThreadExecutor().execute(() -> {
                    AppDatabase.getInstance(getApplicationContext()).transactionDao().update(transaction);
                    runOnUiThread(this::finish);
                });
            } else {
                // Handle case where transaction is null
                // Show error message or handle it gracefully
            }
        } catch (NumberFormatException e) {
            // Handle number format exception for amount
            inputAmount.setError("Invalid amount");
        } catch (Exception e) {
            // Handle other exceptions
            e.printStackTrace();
        }
    }
}

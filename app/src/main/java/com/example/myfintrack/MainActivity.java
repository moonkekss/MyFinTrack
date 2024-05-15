package com.example.myfintrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    private RecyclerView recentTransactions;
    private TransactionAdapter transactionAdapter;
    private ArrayList<Transaction> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recentTransactions = findViewById(R.id.recentTransactions);
        recentTransactions.setLayoutManager(new LinearLayoutManager(this));

        // Placeholder data
        transactionList = new ArrayList<>();
        transactionList.add(new Transaction("Groceries", "$50.00"));
        transactionList.add(new Transaction("Rent", "$1200.00"));

        transactionAdapter = new TransactionAdapter(transactionList);
        recentTransactions.setAdapter(transactionAdapter);

        Button addTransactionButton = findViewById(R.id.addTransactionButton);
        addTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start AddTransactionActivity
                Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
                startActivity(intent);
            }
        });

        // Handle navigation clicks for the Budget screen
        ImageView navBudget = findViewById(R.id.navBudget);
        navBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start BudgetActivity
                Intent intent = new Intent(MainActivity.this, BudgetActivity.class);
                startActivity(intent);
            }
        });

        ImageView navReports = findViewById(R.id.navReports);
        navReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ReportsActivity
                Intent intent = new Intent(MainActivity.this, ReportsActivity.class);
                startActivity(intent);
            }
        });

        ImageView navSettings = findViewById(R.id.navSettings);
        navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SettingsActivity
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}

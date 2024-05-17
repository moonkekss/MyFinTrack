package com.example.myfintrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
public class MainActivity extends AppCompatActivity {

    private RecyclerView recentTransactions;
    private TransactionAdapter transactionAdapter;
    private List<Transaction> transactionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recentTransactions = findViewById(R.id.recentTransactions);
        recentTransactions.setLayoutManager(new LinearLayoutManager(this));

        transactionAdapter = new TransactionAdapter(this,transactionList);
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
        loadTransactions();
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadTransactions();
    }

    private void loadTransactions() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                transactionList = AppDatabase.getInstance(getApplicationContext()).transactionDao().getAllTransactions();
                for (Transaction transaction : transactionList) {
                    Log.d("MainActivity", "Transaction retrieved: " + transaction);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        transactionAdapter.setTransactionList(transactionList);
                        transactionAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}

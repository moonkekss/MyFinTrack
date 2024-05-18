package com.example.myfintrack;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;

import java.util.ArrayList;
import java.util.List;

public class ReportsActivity extends AppCompatActivity {

    private Spinner reportTypeSpinner;
    private AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        // Initialize UI components
        ImageView backButton = findViewById(R.id.backButton);
        anyChartView = findViewById(R.id.any_chart_view);
        reportTypeSpinner = findViewById(R.id.reportTypeSpinner);

        initializeSpinner();
        loadChartData();
        // Set up back button to return to the home screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Return to the previous activity
            }
        });
    }
    private void initializeSpinner() {
        // Initialize the spinner with report types
        String[] reportTypes = {"Monthly", "Yearly"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reportTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reportTypeSpinner.setAdapter(adapter);
    }
    private void loadChartData() {
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        // Example data
        data.add(new ValueDataEntry("Jan", 1000));
        data.add(new ValueDataEntry("Feb", 1500));
        data.add(new ValueDataEntry("Mar", 800));

        cartesian.data(data);
        anyChartView.setChart(cartesian);
    }
}

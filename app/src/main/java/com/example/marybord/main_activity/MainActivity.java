package com.example.marybord.main_activity;
/**
 * @author Mary Bordunova
 * @date 8/11/2020
 * @description This activity is responsible for showing all currencies data in list format
 */
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marybord.R;
import com.example.marybord.adapter.CurrencyAdapter;
import com.example.marybord.model.Currency;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CurrencyContract.View {
    private RecyclerView recyclerView;
    private DatePicker datePicker;
    private ProgressBar progressBar;
    private Button buttonGetRates;

    private CurrencyPresenter currencyPresenter;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        setListeners();

        currencyPresenter = new CurrencyPresenter(this);
    }

    /**
     * initialize the UI components
     */
    private void initUI() {
        datePicker = findViewById(R.id.datePicker);
        progressBar = findViewById(R.id.pb_loading);
        buttonGetRates = findViewById(R.id.buttonGetRates);
        recyclerView = findViewById(R.id.recycler_view_rates);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        hideProgress();
    }

    /**
     * set listeners for all views
     */
    private void setListeners() {
        buttonGetRates.setOnClickListener(view -> {
            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int day = datePicker.getDayOfMonth();

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);

            currencyPresenter.requestDataFromServer(calendar);
        });
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Currency> currencies) {
        CurrencyAdapter adapter = new CurrencyAdapter(currencies);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable, String errorMessage) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        setDataToRecyclerView(new ArrayList<>());
    }

    @Override
    public void onResponseServerError(String errorMessage) {
        Log.e(TAG, errorMessage);
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        setDataToRecyclerView(new ArrayList<>());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        currencyPresenter.onDestroy();
    }
}
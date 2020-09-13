package com.example.marybord.main_activity;
/**
 * @author Mary Bordunova
 * @date 8/12/2020
 * @description This is a model class for list screen, it  handles all business logic
 */
import android.util.Log;

import com.example.marybord.model.Currency;
import com.example.marybord.model.CurrencyResponse;
import com.example.marybord.network.ApiClient;
import com.example.marybord.network.ApiInterface;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.marybord.network.ApiClient.APP_ID;

public class CurrencyModel implements CurrencyContract.Model {

    private static final String TAG = "RatesModel";
    // Date is required  in YYYY-MM-DD format for /historical/*.json
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String ERROR_STATUS = "status";
    public static final String ERROR_DESCRIPTION = "description";

    @Override
    public void getCurrenciesList(OnFinishedListener onFinishedListener, Calendar date) {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<CurrencyResponse> request = apiInterface.getCurrency(getStringFromCalendar(date), APP_ID);
        request.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, retrofit2.Response<CurrencyResponse> response) {

                if (response.isSuccessful()) {
                    CurrencyResponse rateResponse = response.body();

                    HashMap<String, Double> hashMap = rateResponse.getCurrencies();
                    List<Currency> rates = getListCurrenciesFromMap(hashMap);
                    onFinishedListener.onSuccessful(rates);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String errorMessage = String.format("Status Code: %s , description: %s", jObjError.getString(ERROR_STATUS), jObjError.getString(ERROR_DESCRIPTION));
                        onFinishedListener.onServerError(errorMessage);
                    } catch (Exception e) {
                        Log.e(TAG, "onResponse: " + e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable throwable) {
                String error = throwable instanceof IOException ? "No internet connection" : "Converter error";
                onFinishedListener.onFailure(throwable, error);
            }
        });
    }

    /**
     * Convert Calendar object to string YYYY-MM-DD format
     * @param calendar
     * @return
     */
    private String getStringFromCalendar(Calendar calendar){
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE);
        return dateFormat.format(calendar.getTime());
    }

    private List<Currency> getListCurrenciesFromMap(HashMap<String, Double> map) {
        List<Currency> rates = new ArrayList<Currency>();

        for (String key : map.keySet()) {
            double rate = map.get(key);
            rates.add(new Currency(key, rate));
        }

        return rates;
    }
}

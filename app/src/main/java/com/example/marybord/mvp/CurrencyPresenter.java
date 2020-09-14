package com.example.marybord.mvp;
/**
 * @author Mary Bordunova
 * @date 8/12/2020
 * @description Presenter handles the communication between view and model
 */

import android.util.Log;

import com.example.marybord.data.Currency;
import com.example.marybord.data.CurrencyResponse;
import com.example.marybord.network.ApiInterface;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class CurrencyPresenter implements CurrencyContract.Presenter {

    private Retrofit retrofit;
    private CurrencyContract.View view;

    // Date is required  in YYYY-MM-DD format for /historical/*.json
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String ERROR_STATUS = "status";
    public static final String ERROR_DESCRIPTION = "description";
    public static final String APP_ID = "09b4f729325b4889b359397cdde1b72c";

    private static final String TAG = "CurrencyPresenter";
    @Inject
    public CurrencyPresenter(Retrofit retrofit, CurrencyContract.View view) {
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void requestDataFromServer(Calendar date) {
        view.showProgress();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<CurrencyResponse> request = apiInterface.getCurrency(getStringFromCalendar(date), APP_ID);
        request.enqueue(new Callback<CurrencyResponse>() {
            @Override
            public void onResponse(Call<CurrencyResponse> call, retrofit2.Response<CurrencyResponse> response) {

                if (response.isSuccessful()) {
                    CurrencyResponse rateResponse = response.body();

                    HashMap<String, Double> hashMap = rateResponse.getCurrencies();
                    List<Currency> rates = getListCurrenciesFromMap(hashMap);
                    view.onSuccessful(rates);
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String errorMessage = String.format("Status Code: %s , description: %s", jObjError.getString(ERROR_STATUS), jObjError.getString(ERROR_DESCRIPTION));
                        view.onServerError(errorMessage);
                    } catch (Exception e) {
                        Log.e(TAG, "onResponse: " + e.getMessage());
                    }
                }
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<CurrencyResponse> call, Throwable throwable) {
                String error = throwable instanceof IOException ? "No internet connection" : "Converter error";
                view.onFailure(throwable, error);
                view.hideProgress();
            }
        });
    }

    /**
     * Convert Calendar object to string YYYY-MM-DD format
     *
     * @param calendar
     * @return
     */
    private String getStringFromCalendar(Calendar calendar) {
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

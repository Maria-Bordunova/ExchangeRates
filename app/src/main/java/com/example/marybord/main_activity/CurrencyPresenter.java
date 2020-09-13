package com.example.marybord.main_activity;
/**
 * @author Mary Bordunova
 * @date 8/12/2020
 * @description Presenter handles the communication between view and model
 */
import com.example.marybord.model.Currency;

import java.util.Calendar;
import java.util.List;

public class CurrencyPresenter implements CurrencyContract.Presenter, CurrencyContract.Model.OnFinishedListener {


    private CurrencyContract.View currencyListView;
    private CurrencyContract.Model currencyListModel;

    public CurrencyPresenter(CurrencyContract.View currencyListView) {
        this.currencyListView = currencyListView;
        currencyListModel = new CurrencyModel();
    }

    @Override
    public void onDestroy() {
        this.currencyListView = null;
    }

    @Override
    public void requestDataFromServer(Calendar date) {
        if (currencyListView != null) {
            currencyListView.showProgress();
        }

        currencyListModel.getCurrenciesList(this, date);
    }


    @Override
    public void onSuccessful(List<Currency> currencies) {
        currencyListView.setDataToRecyclerView(currencies);
        if (currencyListView != null) {
            currencyListView.hideProgress();
        }
    }

    @Override
    public void onServerError(String errorMessage) {
        currencyListView.onResponseServerError(errorMessage);
        if (currencyListView != null) {
            currencyListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable throwable, String errorMessage) {
        currencyListView.onResponseFailure(throwable, errorMessage);
        if (currencyListView != null) {
            currencyListView.hideProgress();
        }
    }
}

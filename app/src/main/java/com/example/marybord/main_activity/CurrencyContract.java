package com.example.marybord.main_activity;
/**
 * @author Mary Bordunova
 * @date 8/12/2020
 * @description This class has interfaces for model, view and presenter
 */
import com.example.marybord.model.Currency;

import java.util.Calendar;
import java.util.List;

public class CurrencyContract {
    interface Model {

        interface OnFinishedListener {
            /**
             * Successful response from server
             * @param currencies
             */
            void onSuccessful(List<Currency> currencies);
            /**
             * The Open Exchange Rates API will return JSON error messages if something goes wrong
             * @param errorMessage
             */
            void onServerError(String errorMessage);

            /**
             *  There is more than just a failing request: like no internet connection
             * @param throwable
             * @param errorMessage
             */
            void onFailure(Throwable throwable, String errorMessage);
        }

        void getCurrenciesList(OnFinishedListener onFinishedListener, Calendar date);
    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Currency> currencies);

        void onResponseFailure(Throwable throwable, String errorMessage);

        void onResponseServerError(String errorMessage);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer(Calendar date);

    }
}

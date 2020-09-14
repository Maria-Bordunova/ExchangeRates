package com.example.marybord.mvp;
/**
 * @author Mary Bordunova
 * @date 8/12/2020
 * @description This class has interfaces (also known as contracts) for view and presenter
 */
import com.example.marybord.data.Currency;

import java.util.Calendar;
import java.util.List;

public class CurrencyContract {

    public interface View {

        void showProgress();

        void hideProgress();

        /**
         * Successful response from server
         * @param currencies
         */
        void onSuccessful(List<Currency> currencies);
        /**
         *  There is more than just a failing request: like no internet connection
         * @param throwable
         * @param errorMessage
         */
        void onFailure(Throwable throwable, String errorMessage);
        /**
         * The Open Exchange Rates API will return JSON error messages if something goes wrong
         * @param errorMessage
         */
        void onServerError(String errorMessage);
    }

    interface Presenter {

        void requestDataFromServer(Calendar date);

    }
}

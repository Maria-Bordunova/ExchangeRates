package com.example.marybord.data;
/**
 * @author Mary Bordunova
 * @date 8/11/2020
 * @description This class will be useful when we are going to call api for get currencies
 */
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class CurrencyResponse {

    private String baseCurrency;

    @SerializedName("rates")
    private HashMap<String, Double> currencies;

    public HashMap<String, Double> getCurrencies() {
        return currencies;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }
}

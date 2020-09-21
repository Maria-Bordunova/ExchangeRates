package com.example.marybord.data;

import java.util.HashMap;

/**
 * @author Mary Bordunova
 * @date 9/21/2020
 * @description It describes all data returned from the API
 */
public class CurrencyResponse {
    public String disclaimer;
    public String license;
    public Long timestamp;
    public String baseCurrency;
    public HashMap<String, Double> rates;
}

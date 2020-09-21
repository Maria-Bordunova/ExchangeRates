package com.example.marybord.data;
/**
 * @author Mary Bordunova
 * @date 8/11/2020
 * @description It describes rates and is used in CurrencyAdapted in order to fill RecyclerView
 */
public class Rate {
    private String name;
    private Double rate;

    public Rate(String name, Double rate) {
        this.name = name;
        this.rate = rate;
    }
    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

}

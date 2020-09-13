package com.example.marybord.model;
/**
 * @author Mary Bordunova
 * @date 8/11/2020
 * @description This is a pojo class, it contains all  data for Currency
 */
public class Currency {
    private String name;
    private Double rate;

    public Currency(String name, Double rate) {
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

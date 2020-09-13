package com.example.marybord.network;
/**
 * @author Mary Bordunova
 * @date 8/11/2020
 * @description This is an api interface class, it contains all Api call references
 */
import com.example.marybord.model.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("/historical/{date}.json")
    Call<CurrencyResponse> getCurrency(@Path("date") String date, @Query("app_id") String key);
}

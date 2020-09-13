package com.example.marybord.network;
/**
 * @author Mary Bordunova
 * @date 8/12/2020
 * @description This class has all constants related to the networking and retrofit instance
 */
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://openexchangerates.org";
    public static final String APP_ID = "09b4f729325b4889b359397cdde1b72c";
    public static final int CASHE_SIZE = 10 * 1024 * 1024; // 10 MiB

    private volatile static Retrofit instance;

    public static Retrofit getClient() {
        if (instance == null) {                // Single Checked
            synchronized (ApiClient.class) {
                if (instance == null) {        // Double checked

                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    OkHttpClient httpClient = new OkHttpClient.Builder()
                            .addInterceptor(httpLoggingInterceptor)
                            .build();

                     instance = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create(new Gson()))
                            .client(httpClient)
                            .baseUrl(BASE_URL)
                            .build();
                }
            }
        }
        return instance;
    }
}

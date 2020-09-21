package com.example.marybord.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mary Bordunova
 * @date 9/21/2020
 * @description It describes all used data from the API
 */
public class Currency implements Parcelable {

    public Map<String, Double> rates = new HashMap<>();

    private Currency(Parcel in) {
        Bundle bundle = in.readBundle(Double.class.getClassLoader());
        for (String key : bundle.keySet()) {
            rates.put(key, bundle.getDouble(key));
        }
    }

    public Currency() {}

    public static final Creator<Currency> CREATOR = new Creator<Currency>() {
        @Override
        public Currency createFromParcel(Parcel in) {
            return new Currency(in);
        }

        @Override
        public Currency[] newArray(int size) {
            return new Currency[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Bundle bundle = new Bundle();
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            bundle.putDouble(entry.getKey(), entry.getValue());
        }
        parcel.writeBundle(bundle);
    }
}

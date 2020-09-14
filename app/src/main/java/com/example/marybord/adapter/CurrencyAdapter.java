package com.example.marybord.adapter;
/**
 * @author Mary Bordunova
 * @date 8/11/2020
 * @description This class is responsible for showing currencies and rates
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.marybord.R;
import com.example.marybord.data.Currency;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.RateHolder> {
    private List<Currency> rates;

    public CurrencyAdapter(List<Currency> rates) {
        this.rates = rates;
    }

    @Override
    public RateHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rate_item, parent, false);
        return new RateHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RateHolder holder, int position) {
        Currency rate = rates.get(position);
        holder.textViewCurrency.setText(rate.getName());
        holder.textViewRate.setText(rate.getRate().toString());
    }

    @Override
    public int getItemCount() {
        if (rates == null)
            return 0;
        return rates.size();
    }

    class RateHolder extends RecyclerView.ViewHolder {
        private TextView textViewCurrency;
        private TextView textViewRate;

        public RateHolder(View itemView) {
            super(itemView);

            textViewCurrency = itemView.findViewById(R.id.textViewCurrency);
            textViewRate = itemView.findViewById(R.id.textViewRate);
        }
    }
}

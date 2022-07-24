package com.practice.sberclientandroidapp.apapter;

import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;
import com.practice.sberclientandroidapp.model.DayCurrency;
import com.practice.sberclientandroidapp.model.FullCurrencyInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class FullCurrencyInfoAdapter extends RecyclerView.Adapter<FullCurrencyInfoHolder> {

    private List<FullCurrencyInfo> fullCurrencyInfoList;
    private List<Double> listOfDifferences;

    public FullCurrencyInfoAdapter(List<FullCurrencyInfo> fullCurrencyInfoList,
                                   List<Double> listOfDifferences) {
        this.fullCurrencyInfoList = fullCurrencyInfoList;
        this.listOfDifferences = listOfDifferences;
    }

    @NonNull
    @Override
    public FullCurrencyInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_favorite_currency_item, parent, false);
        return new FullCurrencyInfoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FullCurrencyInfoHolder holder, int position) {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        String pattern = ((SimpleDateFormat)formatter).toPattern();
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String textCharCodeAndNominal;
        FullCurrencyInfo fullCurrencyInfo = fullCurrencyInfoList.get(position);
        double difference = listOfDifferences.get(position);
        if (fullCurrencyInfo.getNominal() != 1) {
            textCharCodeAndNominal = fullCurrencyInfo.getNominal()
                    + "\n" + fullCurrencyInfo.getCharcode();
        }
        else {
            textCharCodeAndNominal = fullCurrencyInfo.getCharcode();
        }

        holder.charCodeAndNominal.setText(textCharCodeAndNominal);
        holder.name.setText(fullCurrencyInfo.getName());
        holder.date.setText(dateFormat.format(fullCurrencyInfo.getDate()));
        holder.value.setText(String.format(Locale.getDefault(),
                "%.4f " + Html.fromHtml("&#x20bd", 0), fullCurrencyInfo.getValue()));
        if (difference > 0) {
            holder.difference.setText(String.valueOf("+" + difference));
            holder.difference.setTextColor(Color.GREEN);
        }
        else if (Math.abs(difference) < 1.e-10){
            holder.difference.setText(String.valueOf(difference));
        }
        else {
            holder.difference.setText(String.valueOf(difference));
            holder.difference.setTextColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return fullCurrencyInfoList.size();
    }
}

package com.practice.sberclientandroidapp.apapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;
import com.practice.sberclientandroidapp.model.DayCurrency;
import com.practice.sberclientandroidapp.ui.for_period_page.ForPeriodFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DayCurrencyAdapter extends RecyclerView.Adapter<DayCurrencyHolder> {

    private List<DayCurrency> dayCurrencyList;
    private String currencyCharCode;

    public DayCurrencyAdapter(List<DayCurrency> dayCurrencyList, String currencyCharCode) {
        this.dayCurrencyList = dayCurrencyList;
        this.currencyCharCode = currencyCharCode;
    }

    @NonNull
    @Override
    public DayCurrencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_day_currency_item, parent, false);
        return new DayCurrencyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayCurrencyHolder holder, int position) {
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        String pattern = ((SimpleDateFormat)formatter).toPattern();
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String textCharCodeAndNominal;
        DayCurrency dayCurrency = dayCurrencyList.get(position);

        if (dayCurrency.getNominal() != 1) {
            textCharCodeAndNominal = dayCurrency.getNominal() + "\n" + currencyCharCode;
        }
        else {
            textCharCodeAndNominal = currencyCharCode;
        }

        holder.charCodeAndNominal.setText(textCharCodeAndNominal);
        holder.date.setText(dateFormat.format(dayCurrency.getDate()));
        // Заполнение EditText курсом валюты с точностью до 4-х знаков после запятой и знакои рубля.
        holder.value.setText(String.format(Locale.getDefault(),
                "%.4f " + Html.fromHtml("&#x20bd", 0), dayCurrency.getValue()));
    }

    @Override
    public int getItemCount() {
        return dayCurrencyList.size();
    }
}

package com.practice.sberclientandroidapp.apapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;
import com.practice.sberclientandroidapp.model.DayCurrency;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
// Адаптер для RecyclerView с DayCurrency, использующий как holder DayCurrencyHolder.
public class DayCurrencyAdapter extends RecyclerView.Adapter<DayCurrencyHolder> {
    // Список курсов валюты за разные даты.
    private final List<DayCurrency> dayCurrencyList;
    // Символьный код этой валюты.
    private final String currencyCharCode;
    // Название этой валюты.
    private final String currencyName;
    // Конструктор по всем полям.
    public DayCurrencyAdapter(List<DayCurrency> dayCurrencyList,
                              String currencyCharCode, String currencyName) {
        this.dayCurrencyList = dayCurrencyList;
        this.currencyName = currencyName;
        this.currencyCharCode = currencyCharCode;
    }
    // Метод создания нового holder 'а для помещения туда информации о курсе валюты.
    @NonNull
    @Override
    public DayCurrencyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_day_currency_item, parent, false);
        return new DayCurrencyHolder(view);
    }
    // Метод для заполнения holder 'а.
    @Override
    public void onBindViewHolder(@NonNull DayCurrencyHolder holder, int position) {
        // Получение форматера даты, характерного для определенного места проживания.
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        // Получение строкового паттерна для этого форматера.
        String pattern = ((SimpleDateFormat)formatter).toPattern();
        // Задание необходимого формата.
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String textCharCodeAndNominal;
        DayCurrency dayCurrency = dayCurrencyList.get(position);
        // Если номинал не равен единицы добавить его в строку textCharCodeAndNominal
        // дополнительно к символьному коду, иначе - не добавлять.
        if (dayCurrency.getNominal() != 1) {
            textCharCodeAndNominal = dayCurrency.getNominal() + "\n" + currencyCharCode;
        }
        else {
            textCharCodeAndNominal = currencyCharCode;
        }
        // Заполнение макета данными.
        holder.charCodeAndNominal.setText(textCharCodeAndNominal);
        holder.name.setText(currencyName);
        holder.date.setText(dateFormat.format(dayCurrency.getDate()));
        // Заполнение EditText курсом валюты с точностью до 4-х знаков после запятой и знакои рубля.
        holder.value.setText(String.format(Locale.getDefault(),
                "%.4f " + Html.fromHtml("&#x20bd", 0), dayCurrency.getValue()));
    }
    // Метод для получения количества элементов RecyclerView.
    @Override
    public int getItemCount() {
        return dayCurrencyList.size();
    }
}

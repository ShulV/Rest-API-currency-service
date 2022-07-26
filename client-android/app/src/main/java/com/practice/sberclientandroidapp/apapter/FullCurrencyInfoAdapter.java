package com.practice.sberclientandroidapp.apapter;

import android.graphics.Color;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;
import com.practice.sberclientandroidapp.model.FullCurrencyInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
// Адаптер для RecyclerView с FullCurrencyInfo, использующий как holder FullCurrencyInfoHolder.
public class FullCurrencyInfoAdapter extends RecyclerView.Adapter<FullCurrencyInfoHolder> {
    // Список полной информации о курсах валют за разные даты.
    private final List<FullCurrencyInfo> fullCurrencyInfoList;
    // Список разниц между курсами валюты за определенную дату и за дату за день до нее.
    private final List<Double> listOfDifferences;
    // Конструктор по всем полям.
    public FullCurrencyInfoAdapter(List<FullCurrencyInfo> fullCurrencyInfoList,
                                   List<Double> listOfDifferences) {
        this.fullCurrencyInfoList = fullCurrencyInfoList;
        this.listOfDifferences = listOfDifferences;
    }
    // Метод создания нового holder 'а для помещения туда полной информации о курсе валюты.
    @NonNull
    @Override
    public FullCurrencyInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_favorite_currency_item, parent, false);
        return new FullCurrencyInfoHolder(view);
    }
    // Метод для заполнения holder 'а.
    @Override
    public void onBindViewHolder(@NonNull FullCurrencyInfoHolder holder, int position) {
        // Получение форматера даты, характерного для определенного места проживания.
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        // Получение строкового паттерна для этого форматера.
        String pattern = ((SimpleDateFormat)formatter).toPattern();
        // Задание необходимого формата.
        DateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String textCharCodeAndNominal;
        FullCurrencyInfo fullCurrencyInfo = fullCurrencyInfoList.get(position);
        double difference = listOfDifferences.get(position);
        // Если номинал не равен единицы добавить его в строку textCharCodeAndNominal
        // дополнительно к символьному коду, иначе - не добавлять.
        if (fullCurrencyInfo.getNominal() != 1) {
            textCharCodeAndNominal = fullCurrencyInfo.getNominal()
                    + "\n" + fullCurrencyInfo.getCharcode();
        }
        else {
            textCharCodeAndNominal = fullCurrencyInfo.getCharcode();
        }
        // Заполнение макета данными.
        holder.charCodeAndNominal.setText(textCharCodeAndNominal);
        holder.name.setText(fullCurrencyInfo.getName());
        holder.date.setText(dateFormat.format(fullCurrencyInfo.getDate()));
        // Заполнение EditText курсом валюты с точностью до 4-х знаков после запятой и знакои рубля.
        holder.value.setText(String.format(Locale.getDefault(),
                "%.4f" + Html.fromHtml("&#x20bd", 0), fullCurrencyInfo.getValue()));
        // Если разница больше нуля - дописать спереди + и сделать текст зеленого цвета.
        if (difference > 0) {
            holder.difference.setText(String.format(Locale.getDefault(),
                    "+%.4f ↑", difference));
            holder.difference.setTextColor(Color.parseColor("#197400"));
        }
        // Если разница примерно равна нулю - оставить текст черного цвета.
        else if (Math.abs(difference) < 1.e-10){
            holder.difference.setText(String.format(Locale.getDefault(),
                    "%.4f", difference));
        }
        // Иначе - сделать цвет текста красным.
        else {
            holder.difference.setText(String.format(Locale.getDefault(),
                    "%.4f ↓", difference));
            holder.difference.setTextColor(Color.parseColor("#A60206"));
        }
    }
    // Метод для получения количества элементов RecyclerView.
    @Override
    public int getItemCount() {
        return fullCurrencyInfoList.size();
    }
}

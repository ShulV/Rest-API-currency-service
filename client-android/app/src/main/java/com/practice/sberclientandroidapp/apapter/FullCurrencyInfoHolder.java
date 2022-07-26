package com.practice.sberclientandroidapp.apapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;
// Holder для хранения полной информации об одной валюте за определенную дату.
// Служит как программная модель макета list_favorite_currency_holder.xml.
public class FullCurrencyInfoHolder extends RecyclerView.ViewHolder {
    // Поля макета для символольного кода и номинала, названия валюты,
    // даты и курса валюты за эту дату и разницы между value и value за день до date.
    TextView charCodeAndNominal, name, date, value, difference;
    // Конструктор.
    public FullCurrencyInfoHolder(@NonNull View itemView) {
        super(itemView);
        charCodeAndNominal = itemView.findViewById(R.id.favoriteListItem_charCodeAndNominal);
        name = itemView.findViewById(R.id.favoriteListItem_name);
        date = itemView.findViewById(R.id.favoriteListItem_date);
        value = itemView.findViewById(R.id.favoriteListItem_value);
        difference = itemView.findViewById(R.id.favoriteListItem_difference);
    }
}


package com.practice.sberclientandroidapp.apapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;

// Holder для хранения информации об одной валюте за определенную дату.
// Служит как программная модель макета list_day_currency_holder.xml.
public class DayCurrencyHolder extends RecyclerView.ViewHolder {
    // Поля макета для символольного кода и номинала,
    // названия валюты, даты и курса валюты за эту дату.
    TextView charCodeAndNominal, name, date, value;
    // Конструктор.
    public DayCurrencyHolder(@NonNull View itemView) {
        super(itemView);
        charCodeAndNominal = itemView.findViewById(R.id.listItem_charCodeAndNominal);
        date = itemView.findViewById(R.id.listItem_date);
        value = itemView.findViewById(R.id.listItem_value);
        name = itemView.findViewById(R.id.listItem_name);
    }
}

package com.practice.sberclientandroidapp.apapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;

public class FullCurrencyInfoHolder extends RecyclerView.ViewHolder {

    TextView charCodeAndNominal, date, value, difference;

    public FullCurrencyInfoHolder(@NonNull View itemView) {
        super(itemView);
        charCodeAndNominal = itemView.findViewById(R.id.favoriteListItem_charCodeAndNominal);
        date = itemView.findViewById(R.id.favoriteListItem_date);
        value = itemView.findViewById(R.id.favoriteListItem_value);
        difference = itemView.findViewById(R.id.favoriteListItem_difference);
    }
}


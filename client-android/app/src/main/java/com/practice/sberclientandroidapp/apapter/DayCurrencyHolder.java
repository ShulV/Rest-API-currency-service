package com.practice.sberclientandroidapp.apapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;

public class DayCurrencyHolder extends RecyclerView.ViewHolder {

    TextView charCodeAndNominal, date, value;

    public DayCurrencyHolder(@NonNull View itemView) {
        super(itemView);
        charCodeAndNominal = itemView.findViewById(R.id.listItem_charCodeAndNominal);
        date = itemView.findViewById(R.id.listItem_date);
        value = itemView.findViewById(R.id.listItem_value);
    }
}

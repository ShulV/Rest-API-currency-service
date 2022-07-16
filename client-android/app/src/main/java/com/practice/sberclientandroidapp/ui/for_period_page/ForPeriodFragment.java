package com.practice.sberclientandroidapp.ui.for_period_page;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.practice.sberclientandroidapp.R;
import com.practice.sberclientandroidapp.api.CurrencyAPI;
import com.practice.sberclientandroidapp.databinding.FragmentForPeriodPageBinding;
import com.practice.sberclientandroidapp.model.CurrencyMenuItem;
import com.practice.sberclientandroidapp.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForPeriodFragment extends Fragment {

    private ForPeriodPageViewModel forPeriodPageViewModel;
    private FragmentForPeriodPageBinding binding;
    RetrofitService retrofitService = new RetrofitService();
    private EditText startPeriodDate;
    private EditText endPeriodDate;
    private Calendar date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forPeriodPageViewModel =
                new ViewModelProvider(this).get(ForPeriodPageViewModel.class);

        binding = FragmentForPeriodPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewForPeriodPage;
        forPeriodPageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        loadCurrencyDesignationsFromServer();

        startPeriodDate = root.findViewById(R.id.editText_for_period_start);
        endPeriodDate = root.findViewById(R.id.editText_for_period_end);
        date = Calendar.getInstance();

        initializeDate();

        View.OnClickListener editTextOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.set(year, month, dayOfMonth);
                        month++;
                        String textDay = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        String textMonth = month < 10 ? "0" + month : String.valueOf(month);
                        String textDate = textDay + "." + textMonth + "." + year;
                        EditText currentEditText = (EditText) v;
                        currentEditText.setText(textDate);
                    }
                }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        };

        startPeriodDate.setOnClickListener(editTextOnClickListener);
        endPeriodDate.setOnClickListener(editTextOnClickListener);

        return root;
    }

    private void loadCurrencyDesignationsFromServer() {
        CurrencyAPI currencyAPI = retrofitService.getRetrofit().create(CurrencyAPI.class);
        currencyAPI.getAllCurrencyDesignations()
                .enqueue(new Callback<List<CurrencyMenuItem>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<CurrencyMenuItem>> call, @NonNull Response<List<CurrencyMenuItem>> response) {
                        if (response.body() != null) {
                            populateSpinner(response.body());
                        }
                        else {
                            onNullRequestBody();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<CurrencyMenuItem>> call, @NonNull Throwable t) {
                        Toast.makeText(getActivity(), "Ошибка загрузки данных с сервера", Toast.LENGTH_LONG).show();
                    }

                    public void onNullRequestBody() {
                        Toast.makeText(getActivity(), "Ошибка запроса данных с cbr API", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void populateSpinner(List<CurrencyMenuItem> responseBody) {
        Spinner spinner = requireView().findViewById(R.id.spinner);
        List<String> currencyDesignations = new ArrayList<>();
        for (CurrencyMenuItem currencyMenuItem: responseBody) {
            currencyDesignations.add(currencyMenuItem.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, currencyDesignations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initializeDate() {
        startPeriodDate.setText(DateUtils.formatDateTime(getActivity(),
                date.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
        endPeriodDate.setText(DateUtils.formatDateTime(getActivity(),
                date.getTimeInMillis(),
                DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
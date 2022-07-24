package com.practice.sberclientandroidapp.ui.for_period_page;


import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;
import com.practice.sberclientandroidapp.apapter.DayCurrencyAdapter;
import com.practice.sberclientandroidapp.api.CurrencyMenuItemAPI;
import com.practice.sberclientandroidapp.api.DayCurrencyAPI;
import com.practice.sberclientandroidapp.databinding.FragmentForPeriodPageBinding;
import com.practice.sberclientandroidapp.model.CurrencyMenuItem;
import com.practice.sberclientandroidapp.model.DayCurrency;
import com.practice.sberclientandroidapp.retrofit.RetrofitService;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForPeriodFragment extends Fragment {

    private ForPeriodPageViewModel forPeriodPageViewModel;
    private FragmentForPeriodPageBinding binding;
    private RetrofitService retrofitService;
    private List<CurrencyMenuItem> currencyMenuItems;
    private Spinner spinner;
    private EditText startPeriodDate;
    private EditText endPeriodDate;
    private Date startDate;
    private Date endDate;
    private Calendar date;
    private Button getCurrenciesForPeriodButton;
    private RecyclerView dayCurrencyRecyclerView;
    private String serverURL;
    private ProgressBar progressBar;

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

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(root.getContext());
        serverURL = preferences.getString("URL", "");

        retrofitService = new RetrofitService(serverURL);

        startDate = new Date(Calendar.getInstance().getTimeInMillis());
        endDate = new Date(Calendar.getInstance().getTimeInMillis());

        spinner = root.findViewById(R.id.spinner);

        loadCurrencyDesignationsFromServer();

        startPeriodDate = root.findViewById(R.id.editText_for_period_start);
        endPeriodDate = root.findViewById(R.id.editText_for_period_end);
        getCurrenciesForPeriodButton = root.findViewById(R.id.button_get_currencies_for_period);
        date = Calendar.getInstance();

        initializeDate();

        progressBar = root.findViewById(R.id.progressBar_for_period_page);
        dayCurrencyRecyclerView = root.findViewById(R.id.recyclerView_dayCurrency);
        dayCurrencyRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dayCurrencyRecyclerView
                .addItemDecoration(new DividerItemDecoration(dayCurrencyRecyclerView.getContext(),
                        DividerItemDecoration.VERTICAL));

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
        getCurrenciesForPeriodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatesFromEditText();
                if (isDataCorrect()) {
                    progressBar.setVisibility(View.VISIBLE);
                    loadCurrenciesForPeriod();
                }
                else {
                    Toast.makeText(getActivity(),
                            "Неверный ввод периода", Toast.LENGTH_LONG).show();
                }
            }
        });

        return root;
    }

    private void getDatesFromEditText() {
        DateFormat inDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        try {
            startDate = new Date(Objects.requireNonNull(
                    inDateFormat.parse(startPeriodDate.getText().toString())).getTime());
            endDate = new Date(Objects.requireNonNull(
                    inDateFormat.parse(endPeriodDate.getText().toString())).getTime());
        }
        catch (java.text.ParseException e) {
            Toast.makeText(getActivity(), "Неверный формат даты", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isDataCorrect() {
        return !endDate.before(startDate) && !startDate.after(Calendar.getInstance().getTime())
                && !endDate.after(Calendar.getInstance().getTime());
    }

    private void loadCurrenciesForPeriod() {
        DateFormat inDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date startDate = new Date(Calendar.getInstance().getTimeInMillis()),
                endDate = new Date(Calendar.getInstance().getTimeInMillis());
        try {
            startDate = new Date(Objects.requireNonNull(
                    inDateFormat.parse(startPeriodDate.getText().toString())).getTime());
            endDate = new Date(Objects.requireNonNull(
                    inDateFormat.parse(endPeriodDate.getText().toString())).getTime());
        }
        catch (java.text.ParseException e) {
            Toast.makeText(getActivity(), "Неверный формат даты", Toast.LENGTH_LONG).show();
        }
        String currencyName = spinner.getSelectedItem().toString();
        String charCode = getCurrencyCharCodeByDesignation(currencyName);
        DayCurrencyAPI dayCurrencyAPI = retrofitService.getRetrofit().create(DayCurrencyAPI.class);
        dayCurrencyAPI.getCurrenciesForPeriod(startDate, endDate, charCode)
                .enqueue(new Callback<List<DayCurrency>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<DayCurrency>> call,
                                           @NonNull Response<List<DayCurrency>> response) {
                        populateRecyclerView(response.body(), charCode, currencyName);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<DayCurrency>> call, @NonNull Throwable t) {
                        Toast.makeText(getActivity(), "Ошибка загрузки данных с сервера", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void loadCurrencyDesignationsFromServer() {
        CurrencyMenuItemAPI currencyMenuItemAPI = retrofitService.getRetrofit().create(CurrencyMenuItemAPI.class);
        currencyMenuItemAPI.getAllCurrencyDesignations()
                .enqueue(new Callback<List<CurrencyMenuItem>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<CurrencyMenuItem>> call,
                                           @NonNull Response<List<CurrencyMenuItem>> response) {
                        currencyMenuItems = response.body();
                        if (currencyMenuItems != null) {
                            populateSpinner(currencyMenuItems);
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

    private String getCurrencyCharCodeByDesignation(String currencyDesignation) {
        for (CurrencyMenuItem currency: currencyMenuItems) {
            if (currency.getName().equals(currencyDesignation)) {
                return currency.getCharCode();
            }
        }
        return "";
    }

    private void populateRecyclerView(List<DayCurrency> responseBody,
                                      String currencyCharCode, String currencyName) {
        DayCurrencyAdapter dayCurrencyAdapter =
                new DayCurrencyAdapter(responseBody, currencyCharCode, currencyName);
        dayCurrencyRecyclerView.setAdapter(dayCurrencyAdapter);
    }

    private void populateSpinner(List<CurrencyMenuItem> currencyMenuItems) {

        List<String> currencyDesignations = new ArrayList<>();
        for (CurrencyMenuItem currencyMenuItem: currencyMenuItems) {
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
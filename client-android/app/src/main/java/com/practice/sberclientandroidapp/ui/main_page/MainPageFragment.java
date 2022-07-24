package com.practice.sberclientandroidapp.ui.main_page;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.practice.sberclientandroidapp.R;
import com.practice.sberclientandroidapp.apapter.FullCurrencyInfoAdapter;
import com.practice.sberclientandroidapp.api.FullCurrencyInfoAPI;
import com.practice.sberclientandroidapp.databinding.FragmentMainPageBinding;
import com.practice.sberclientandroidapp.model.DayCurrency;
import com.practice.sberclientandroidapp.model.FullCurrencyInfo;
import com.practice.sberclientandroidapp.retrofit.RetrofitService;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPageFragment extends Fragment {

    private MainPageViewModel mainPageViewModel;
    private FragmentMainPageBinding binding;
    private RetrofitService retrofitService;
    private RecyclerView fullCurrencyInfoRecyclerView;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainPageViewModel =
                new ViewModelProvider(this).get(MainPageViewModel.class);

        binding = FragmentMainPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(root.getContext());
        String serverURL = preferences.getString("URL", "");

        retrofitService = new RetrofitService(serverURL);

        Set<String> currencyHashSet =
                preferences.getStringSet("favorite_currencies", new HashSet<>());

        progressBar = root.findViewById(R.id.progressBar_main_page);
        fullCurrencyInfoRecyclerView = root.findViewById(R.id.recyclerView_fullCurrencyInfo);
        fullCurrencyInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fullCurrencyInfoRecyclerView
                .addItemDecoration(new DividerItemDecoration(fullCurrencyInfoRecyclerView.getContext(),
                        DividerItemDecoration.VERTICAL));

        loadAllCurrenciesForToday(currencyHashSet);


        return root;
    }

    private void loadAllCurrenciesForToday(Set<String> currencyHashSet) {
        DateFormat inDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date today = new Date(Calendar.getInstance().getTimeInMillis());

        FullCurrencyInfoAPI fullCurrencyInfoAPI =
                retrofitService.getRetrofit().create(FullCurrencyInfoAPI.class);
        fullCurrencyInfoAPI.getAllCurrenciesForDay(today)
                .enqueue(new Callback<List<FullCurrencyInfo>>() {
            @Override
            public void onResponse(@NonNull Call<List<FullCurrencyInfo>> call,
                                   @NonNull Response<List<FullCurrencyInfo>> response) {
                if (response.body() == null) {
                    onNullRequestBody();
                    return;
                }
                loadAllCurrenciesForYesterday(response.body(), currencyHashSet);
            }

            @Override
            public void onFailure(@NonNull Call<List<FullCurrencyInfo>> call,
                                  @NonNull Throwable t) {
                Toast.makeText(getActivity(), "Ошибка загрузки данных с сервера", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }

            public void onNullRequestBody() {
                Toast.makeText(getActivity(), "Ошибка запроса данных с cbr API",
                        Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void loadAllCurrenciesForYesterday(List<FullCurrencyInfo> fullCurrencyInfoListForToday,
                                               Set<String> currencyHashSet) {
        DateFormat inDateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        Date yesterday = new Date(Calendar.getInstance().getTimeInMillis());

        FullCurrencyInfoAPI fullCurrencyInfoAPI =
                retrofitService.getRetrofit().create(FullCurrencyInfoAPI.class);
        fullCurrencyInfoAPI.getAllCurrenciesForDay(yesterday)
                .enqueue(new Callback<List<FullCurrencyInfo>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<FullCurrencyInfo>> call,
                                           @NonNull Response<List<FullCurrencyInfo>> response) {
                        deleteUnnecessaryDataFromList(fullCurrencyInfoListForToday, currencyHashSet);
                        if (response.body() == null) {
                            onNullRequestBody();
                            return;
                        }
                        deleteUnnecessaryDataFromList(response.body(), currencyHashSet);
                        List<Double> listOfDifferences =
                                calculateListOfDifferences(fullCurrencyInfoListForToday,
                                        response.body());
                        populateRecyclerView(fullCurrencyInfoListForToday, listOfDifferences);
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<FullCurrencyInfo>> call,
                                          @NonNull Throwable t) {
                        Toast.makeText(getActivity(), "Ошибка загрузки данных с сервера",
                                Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }

                    public void onNullRequestBody() {
                        Toast.makeText(getActivity(), "Ошибка запроса данных с cbr API",
                                Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    private void deleteUnnecessaryDataFromList(List<FullCurrencyInfo> currencyInfos,
                                               Set<String> currencyHashSet) {
        currencyInfos.removeIf(currencyInfo ->
                !currencyHashSet.contains(currencyInfo.getCharcode()));
    }

    private List<Double> calculateListOfDifferences(
            List<FullCurrencyInfo> fullCurrencyInfoListForToday,
            List<FullCurrencyInfo> fullCurrencyInfoListForYesterday) {
        List<Double> listOfDifferences = new ArrayList<>();
        int listSize = fullCurrencyInfoListForToday.size();
        double difference;
        for (int i = 0; i < listSize; i++) {
            difference = fullCurrencyInfoListForToday.get(i).getValue() -
                    fullCurrencyInfoListForYesterday.get(i).getValue();
            listOfDifferences.add(difference);
        }
        return listOfDifferences;
    }

    private void populateRecyclerView(List<FullCurrencyInfo> responseBody,
                                      List<Double> listOfDifferences) {
        FullCurrencyInfoAdapter fullCurrencyInfoAdapter =
                new FullCurrencyInfoAdapter(responseBody, listOfDifferences);
        fullCurrencyInfoRecyclerView.setAdapter(fullCurrencyInfoAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
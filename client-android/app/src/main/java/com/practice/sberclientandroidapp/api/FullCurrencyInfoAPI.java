package com.practice.sberclientandroidapp.api;

import com.practice.sberclientandroidapp.model.FullCurrencyInfo;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FullCurrencyInfoAPI {
    @GET("/api/currency/all-currencies-for-day?")
    Call<List<FullCurrencyInfo>> getAllCurrenciesForDay(@Query("date") Date date);
}

package com.practice.sberclientandroidapp.api;

import com.practice.sberclientandroidapp.model.DayCurrency;

import java.sql.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
// API для получения от сервера курсов валюты
// с символьным кодом charCode за период [fromDate, toDate].
public interface DayCurrencyAPI {
    @GET("/api/currency/period-currencies?")
    Call<List<DayCurrency>> getCurrenciesForPeriod(@Query("fromDate") Date fromDate,
                                                   @Query("toDate") Date toDate,
                                                   @Query("charcode") String charCode);
}

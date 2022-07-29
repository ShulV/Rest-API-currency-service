package com.practice.sberclientandroidapp.api;

import com.practice.sberclientandroidapp.model.CurrencyMenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
// API для получения от сервера обозначений всех валют.
public interface CurrencyMenuItemAPI {
    @GET("/api/currency/all-currency-designations")
    Call<List<CurrencyMenuItem>> getAllCurrencyDesignations();
}

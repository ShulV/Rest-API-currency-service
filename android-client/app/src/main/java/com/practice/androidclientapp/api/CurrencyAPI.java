package com.practice.androidclientapp.api;

import com.practice.androidclientapp.model.CurrencyMenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyAPI {
    @GET("/api/currency/all-currency-designations")
    Call<List<CurrencyMenuItem>> getAllCurrencyDesignations();
}

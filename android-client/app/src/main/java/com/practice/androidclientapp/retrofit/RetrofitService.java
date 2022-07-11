package com.practice.androidclientapp.retrofit;

import com.google.gson.Gson;
import com.practice.androidclientapp.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Object of this class is used to connect to server.
public class RetrofitService {
    private Retrofit retrofit;
    // Constructor with initializing retrofit field.
    public RetrofitService() {
        initializeRetrofit();
    }
    // Method that initializes Retrofit object.
    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                // Server Url address that is defined in string resources.
                .baseUrl(String.valueOf(R.string.server_url))
                // Adding Converter Factory to process json.
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
    // Getter method for retrofit field.
    public Retrofit getRetrofit() {
        return retrofit;
    }
}

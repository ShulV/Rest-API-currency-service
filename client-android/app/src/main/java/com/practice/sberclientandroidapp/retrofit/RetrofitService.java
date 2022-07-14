package com.practice.sberclientandroidapp.retrofit;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.practice.sberclientandroidapp.R;

import org.w3c.dom.Text;

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
                .baseUrl("http://192.168.1.4:8080")
                // Adding Converter Factory to process json.
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }
    // Getter method for retrofit field.
    public Retrofit getRetrofit() {
        return retrofit;
    }
}

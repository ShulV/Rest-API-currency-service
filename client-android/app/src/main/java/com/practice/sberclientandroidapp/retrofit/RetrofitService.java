package com.practice.sberclientandroidapp.retrofit;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.practice.sberclientandroidapp.R;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.sql.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Object of this class is used to connect to server.
public class RetrofitService {
    private Retrofit retrofit;
    private String serverURL;
    // Constructor with initializing retrofit field.
    public RetrofitService(String serverURL) {
        this.serverURL = serverURL;
        initializeRetrofit();
    }
    // Method that initializes Retrofit object.
    private void initializeRetrofit() {
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        retrofit = new Retrofit.Builder()
                // Server Url address that is defined in string resources.
                .baseUrl(serverURL)
                // Adding Converter Factory to process json.
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .build();
    }
    // Getter method for retrofit field.
    public Retrofit getRetrofit() {
        return retrofit;
    }
}

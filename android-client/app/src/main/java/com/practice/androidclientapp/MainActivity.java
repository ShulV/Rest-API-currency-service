package com.practice.androidclientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Создание своего тулбара и установка центрированного текста.
        MaterialToolbar materialToolbar = findViewById(R.id.toolbar);
        materialToolbar.setTitle(R.string.text_MainForm);
        materialToolbar.setTitleCentered(true);
        setSupportActionBar(materialToolbar);
    }
}
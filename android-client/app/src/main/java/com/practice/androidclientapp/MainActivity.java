package com.practice.androidclientapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Создание своего тулбара и установка центрированного текста.
        MaterialToolbar materialToolbar = findViewById(R.id.toolbar);
        materialToolbar.setTitle(R.string.text_MainForm);
        materialToolbar.setTitleCentered(true);
        setSupportActionBar(materialToolbar);

//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.mainItem);


        // Initialize and assign variable
        //BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
       // bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        /*bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.mainItem:
                        return true;
                    case R.id.forPeriod:
                        startActivity(new Intent(getApplicationContext(), ForPeriodFragment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.other:
                        startActivity(new Intent(getApplicationContext(), OtherFragment.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });*/
    }

//    MainFragment mainFragment = new MainFragment();
//    ForPeriodFragment forPeriodFragment = new ForPeriodFragment();
//    OtherFragment otherFragment = new OtherFragment();
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.mainItem:
//                getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.container, mainFragment).commit();
//                return true;
//
//            case R.id.forPeriod:
//                getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.container, forPeriodFragment).commit();
//                return true;
//
//            case R.id.other:
//                getSupportFragmentManager()
//                        .beginTransaction().replace(R.id.container, otherFragment).commit();
//                return true;
//        }
//        return false;
//    }
}
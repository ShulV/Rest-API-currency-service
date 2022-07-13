package com.practice.sberclientandroidapp.ui.main_page;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainPageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MainPageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Главная страница");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
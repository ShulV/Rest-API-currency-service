package com.practice.sberclientandroidapp.ui.for_period_page;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ForPeriodPageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ForPeriodPageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Получение курса валюты за период");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
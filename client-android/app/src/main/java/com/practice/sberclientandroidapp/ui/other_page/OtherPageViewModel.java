package com.practice.sberclientandroidapp.ui.other_page;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OtherPageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OtherPageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Страница с другими\nвозможностями приложения");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
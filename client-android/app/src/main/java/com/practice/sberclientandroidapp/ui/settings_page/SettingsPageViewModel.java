package com.practice.sberclientandroidapp.ui.settings_page;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsPageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsPageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Страница с другими\nвозможностями приложения");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
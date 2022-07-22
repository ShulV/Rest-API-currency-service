package com.practice.sberclientandroidapp.ui.settings_page;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

import com.practice.sberclientandroidapp.R;

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // below line is used to add preference
        // fragment from our xml folder.
        addPreferencesFromResource(R.xml.fragment_preferences);
    }
}

package com.practice.sberclientandroidapp.ui.settings_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.practice.sberclientandroidapp.R;
import com.practice.sberclientandroidapp.databinding.FragmentSettingsPageBinding;

public class SettingsPageFragment extends Fragment {

    private SettingsPageViewModel settingsPageViewModel;
    private FragmentSettingsPageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsPageViewModel =
                new ViewModelProvider(this).get(SettingsPageViewModel.class);

        binding = FragmentSettingsPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if (root.findViewById(R.id.frameLayout_settings) != null) {
            // below line is to inflate our fragment.
            requireActivity().getFragmentManager().beginTransaction()
                    .add(R.id.frameLayout_settings, new SettingsFragment()).commit();
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
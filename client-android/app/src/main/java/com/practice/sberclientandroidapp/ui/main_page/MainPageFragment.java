package com.practice.sberclientandroidapp.ui.main_page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.practice.sberclientandroidapp.databinding.FragmentMainPageBinding;

public class MainPageFragment extends Fragment {

    private MainPageViewModel mainPageViewModel;
    private FragmentMainPageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mainPageViewModel =
                new ViewModelProvider(this).get(MainPageViewModel.class);

        binding = FragmentMainPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMainPage;
        mainPageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
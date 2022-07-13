package com.practice.sberclientandroidapp.ui.for_period_page;

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

import com.practice.sberclientandroidapp.databinding.FragmentForPeriodPageBinding;

public class ForPeriodFragment extends Fragment {

    private ForPeriodPageViewModel forPeriodPageViewModel;
    private FragmentForPeriodPageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        forPeriodPageViewModel =
                new ViewModelProvider(this).get(ForPeriodPageViewModel.class);

        binding = FragmentForPeriodPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textForPeriodPage;
        forPeriodPageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
package com.practice.sberclientandroidapp.ui.other_page;

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

import com.practice.sberclientandroidapp.databinding.FragmentOtherPageBinding;

public class OtherPageFragment extends Fragment {

    private OtherPageViewModel otherPageViewModel;
    private FragmentOtherPageBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        otherPageViewModel =
                new ViewModelProvider(this).get(OtherPageViewModel.class);

        binding = FragmentOtherPageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textOtherPage;
        otherPageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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
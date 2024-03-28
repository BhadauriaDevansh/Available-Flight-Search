package com.example.easyscour.ui.myBooking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyscour.databinding.FragmentMybookingBinding;

public class MyBookingFragment extends Fragment {

    private FragmentMybookingBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyBookingViewModel myBookingViewModel =
                new ViewModelProvider(this).get(MyBookingViewModel.class);

        binding = FragmentMybookingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyBooking;
        myBookingViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}






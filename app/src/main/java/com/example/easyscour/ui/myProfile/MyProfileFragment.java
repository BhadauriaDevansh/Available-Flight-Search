package com.example.easyscour.ui.myProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.easyscour.databinding.FragmentMyprofileBinding;

public class MyProfileFragment extends Fragment{

    private FragmentMyprofileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MyProfileViewModel myProfileViewModel =
                new ViewModelProvider(this).get(MyProfileViewModel.class);

        binding = FragmentMyprofileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textMyProfile;
        myProfileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}





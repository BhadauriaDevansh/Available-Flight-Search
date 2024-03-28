package com.example.easyscour.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.easyscour.R;
import com.example.easyscour.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        // Set up click listeners without using setOnClickListener

        binding.imageButton.setOnClickListener(this::onSearchFlightClick);
        binding.imageButton5.setOnClickListener(this::onMyBookingClick);
        binding.imageButton4.setOnClickListener(this::onMyProfileClick);


        return root;
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // Handle click event for Search Flight button
    public void onSearchFlightClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_searchFlight);
    }

    // Handle click event for My Booking button
    public void onMyBookingClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_myBooking);
    }

    // Handle click event for My Profile button
    public void onMyProfileClick(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_myProfile);
    }
}

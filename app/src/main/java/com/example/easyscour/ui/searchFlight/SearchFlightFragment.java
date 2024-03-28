package com.example.easyscour.ui.searchFlight;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.easyscour.R;
import com.example.easyscour.database.FlightDatabaseHelper;

public class SearchFlightFragment extends Fragment {

    private EditText fromStateEditText, destinationStateEditText, dateEditText, passengersEditText;
    private Button searchButton;
    private FlightDatabaseHelper databaseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize database helper and insert initial flight data
        databaseHelper = new FlightDatabaseHelper(requireContext());
        databaseHelper.insertFlight("Mumbai", "Delhi", "10/04/2024", 1);
        databaseHelper.insertFlight("Delhi", "Mumbai", "11/04/2024", 1);
        databaseHelper.insertFlight("Chennai", "Mumbai", "12/04/2024", 1);
        databaseHelper.insertFlight("Mumbai", "Chennai", "13/04/2024", 1);
        databaseHelper.insertFlight("Chennai", "Delhi", "14/04/2024", 1);
        databaseHelper.insertFlight("Delhi", "Chennai", "15/04/2024", 1);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_searchflight, container, false);

        // Initialize views
        fromStateEditText = root.findViewById(R.id.fromStateEditText);
        destinationStateEditText = root.findViewById(R.id.destinationStateEditText);
        dateEditText = root.findViewById(R.id.dateEditText);
        passengersEditText = root.findViewById(R.id.passengersEditText);
        searchButton = root.findViewById(R.id.searchButton);

        // Initialize NavController
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        // Set click listener for search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve user input
                String fromState = fromStateEditText.getText().toString().trim();
                String destinationState = destinationStateEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();
                int passengers = Integer.parseInt(passengersEditText.getText().toString().trim());

                Cursor cursor = databaseHelper.searchFlights(fromState, destinationState, date, passengers);


                // Navigate to FlightDetailsFragment regardless of cursor result

                Bundle args = new Bundle();
                args.putString("fromState", fromState);
                args.putString("destinationState", destinationState);
                args.putString("date", date);
                args.putInt("passengers", passengers);

                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.action_nav_searchFlight_to_nav_flightDetails, args);

                // Close the cursor after use
                if (cursor != null) {
                    cursor.close();
                }
            }
        });

        return root;
    }
}



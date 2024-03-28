package com.example.easyscour.ui.flightDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.easyscour.R;

public class FlightDetailsFragment extends Fragment {

    private TextView textFromState, textDestinationState, textDate, textPassengers;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_flight_details, container, false);

        // Initialize TextViews
        textFromState = root.findViewById(R.id.textFromState);
        textDestinationState = root.findViewById(R.id.textDestinationState);
        textDate = root.findViewById(R.id.textDate);
        textPassengers = root.findViewById(R.id.textPassengers);

        // Receive flight details data from arguments bundle
        Bundle args = getArguments();
        if (args != null) {
            String fromState = args.getString("fromState");
            String destinationState = args.getString("destinationState");
            String date = args.getString("date");
            int passengers = args.getInt("passengers");

            // Set flight details in TextViews
            textFromState.setText(getString(R.string.from_state_label, fromState));
            textDestinationState.setText(getString(R.string.destination_state_label, destinationState));
            textDate.setText(getString(R.string.date_label, date));
            textPassengers.setText(getString(R.string.passengers_label, String.valueOf(passengers)));
        }

        return root;
    }
}

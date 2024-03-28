package com.example.easyscour.ui.searchFlight;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SearchFlightViewModel extends ViewModel{
    private final MutableLiveData<String> mText;

    public SearchFlightViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Search Flight fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}



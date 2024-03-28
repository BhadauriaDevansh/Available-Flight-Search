package com.example.easyscour.ui.myProfile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyProfileViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public MyProfileViewModel() {
        mText = new MutableLiveData<>();
}
    public LiveData<String> getText() {
        return mText;
    }

}
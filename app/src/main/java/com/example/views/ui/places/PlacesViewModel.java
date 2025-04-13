package com.example.views.ui.places;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlacesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PlacesViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}
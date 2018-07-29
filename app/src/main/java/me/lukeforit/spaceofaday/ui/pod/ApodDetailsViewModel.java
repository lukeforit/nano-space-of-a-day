package me.lukeforit.spaceofaday.ui.pod;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import me.lukeforit.spaceofaday.data.model.Apod;

public class ApodDetailsViewModel extends ViewModel {

    MutableLiveData<Apod> apodLiveData = new MutableLiveData<>();

    @Inject
    public ApodDetailsViewModel() {
    }
}

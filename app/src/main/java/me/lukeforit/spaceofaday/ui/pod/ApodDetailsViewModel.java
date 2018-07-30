package me.lukeforit.spaceofaday.ui.pod;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;

public class ApodDetailsViewModel extends ViewModel {

    private MutableLiveData<Apod> apodLiveData = new MutableLiveData<>();

    @Inject
    SpaceRepository repository;

    @Inject
    public ApodDetailsViewModel() {
    }

    public MutableLiveData<Apod> getApodLiveData() {
        return apodLiveData;
    }
}

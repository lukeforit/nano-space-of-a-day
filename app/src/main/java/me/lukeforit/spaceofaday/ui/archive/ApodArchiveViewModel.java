package me.lukeforit.spaceofaday.ui.archive;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import me.lukeforit.spaceofaday.data.model.Apod;

public class ApodArchiveViewModel extends ViewModel {

    private MutableLiveData<List<Apod>> apodListLiveData = new MutableLiveData<>();

    @Inject
    public ApodArchiveViewModel() {
    }

    void init() {
    }

    public MutableLiveData<List<Apod>> getApodListLiveData() {
        return apodListLiveData;
    }
}

package me.lukeforit.spaceofaday.ui.home;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import me.lukeforit.spaceofaday.ui.base.Event;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<Event<String>> openApodScreen = new MutableLiveData<>();

    @Inject
    public HomeViewModel() {
    }

    public void displayApod(String date) {
        openApodScreen.postValue(new Event<>(date));
    }

    public MutableLiveData<Event<String>> getOpenApodScreen() {
        return openApodScreen;
    }
}

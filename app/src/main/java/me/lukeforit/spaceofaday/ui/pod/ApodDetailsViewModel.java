package me.lukeforit.spaceofaday.ui.pod;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;

public class ApodDetailsViewModel extends ViewModel {

    private MutableLiveData<Apod> apodLiveData = new MutableLiveData<>();

    @Inject
    SpaceRepository repository;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public ApodDetailsViewModel() {
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }

    void init() {
        disposable.add(
                repository.fetchApod()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Apod>() {
                            @Override
                            public void accept(Apod apod) {
                                apodLiveData.postValue(apod);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {

                            }
                        }));
    }

    public MutableLiveData<Apod> getApodLiveData() {
        return apodLiveData;
    }
}

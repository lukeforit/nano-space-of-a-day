package me.lukeforit.spaceofaday.ui.archive;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;

public class ApodArchiveViewModel extends ViewModel {

    private MutableLiveData<List<Apod>> apodListLiveData = new MutableLiveData<>();

    @Inject
    SpaceRepository repository;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public ApodArchiveViewModel() {
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }

    void init() {
        disposable.add(
                repository.fetchAllApods()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Apod>>() {
                            @Override
                            public void accept(List<Apod> list) {
                                apodListLiveData.postValue(list);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                Log.d("ERROR", "error");
                            }
                        }));
    }

    public MutableLiveData<List<Apod>> getApodListLiveData() {
        return apodListLiveData;
    }
}

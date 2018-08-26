package me.lukeforit.spaceofaday.ui.archive;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.lukeforit.spaceofaday.common.Utils;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;
import me.lukeforit.spaceofaday.ui.archive.items.ApodItem;
import me.lukeforit.spaceofaday.ui.archive.items.ArchiveItem;
import me.lukeforit.spaceofaday.ui.archive.items.EmptyApodItem;

public class ApodArchiveViewModel extends ViewModel {

    @Inject
    SpaceRepository repository;
    private MutableLiveData<List<ArchiveItem>> apodListLiveData = new MutableLiveData<>();
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
                        .map(new Function<List<Apod>, List<Apod>>() {
                            @Override
                            public List<Apod> apply(List<Apod> list) {
                                Collections.sort(list, new Comparator<Apod>() {
                                    @Override
                                    public int compare(Apod o1, Apod o2) {
                                        return o1.getDate().compareTo(o2.getDate());
                                    }
                                });
                                return list;
                            }
                        })
                        .map(new Function<List<Apod>, List<ArchiveItem>>() {
                            @Override
                            public List<ArchiveItem> apply(List<Apod> list) throws ParseException {
                                Map<String, Apod> apodsByDate = new HashMap<>();
                                for (Apod apod : list) {
                                    apodsByDate.put(apod.getDate(), apod);
                                }
                                List<String> dates = Utils.getDatesInRange(
                                        list.get(0).getDate(),
                                        list.get(list.size() - 1).getDate(),
                                        10);
                                List<ArchiveItem> result = new ArrayList<>();
                                for (String date: dates) {
                                    if (apodsByDate.containsKey(date)) {
                                        result.add(new ApodItem(apodsByDate.get(date)));
                                    } else {
                                        result.add(new EmptyApodItem(date));
                                    }
                                }
                                return result;
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<ArchiveItem>>() {
                            @Override
                            public void accept(List<ArchiveItem> list) {
                                apodListLiveData.postValue(list);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                Log.d("ERROR", "error");
                            }
                        }));
    }

    public MutableLiveData<List<ArchiveItem>> getArchiveItemListLiveData() {
        return apodListLiveData;
    }
}

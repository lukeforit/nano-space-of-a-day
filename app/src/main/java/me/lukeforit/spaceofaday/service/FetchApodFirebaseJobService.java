package me.lukeforit.spaceofaday.service;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.lukeforit.spaceofaday.common.Utils;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;

public class FetchApodFirebaseJobService extends JobService {

    @Inject
    SpaceRepository repository;

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    public boolean onStartJob(final JobParameters job) {
        disposable.add(
                repository.fetchApod(Utils.getDefaultDateAsString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Apod>() {
                            @Override
                            public void accept(Apod apod) {
                                jobFinished(job, false);
                                //TODO notify about success
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                //TODO log error
                                jobFinished(job, true);
                            }
                        })
        );
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        disposable.clear();
        return true;
    }
}

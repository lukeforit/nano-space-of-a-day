package me.lukeforit.spaceofaday.data.source;

import android.arch.persistence.room.EmptyResultSetException;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.cache.ApodDao;
import me.lukeforit.spaceofaday.data.source.cache.ApodEntity;

public class SpaceRepositoryImpl implements SpaceRepository {

    private ApodRepository repository;
    private ApodDao apodDao;

    public SpaceRepositoryImpl(ApodRepository repository, ApodDao apodDao) {
        this.repository = repository;
        this.apodDao = apodDao;
    }

    @Override
    public Single<List<Apod>> fetchAllApods() {
        return null;
    }

    @Override
    public Single<Apod> fetchApod() {
        //TODO current date ID
        return apodDao.fetchBy(20180804)
                .map(new Function<ApodEntity, Apod>() {
                    @Override
                    public Apod apply(ApodEntity apodEntity) {
                        //TODO mapper
                        return new Apod();
                    }
                })
                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends Apod>>() {
                    @Override
                    public SingleSource<? extends Apod> apply(Throwable throwable) {
                        if (throwable instanceof EmptyResultSetException) {
                            return repository.fetchApod().doOnSuccess(new Consumer<Apod>() {
                                @Override
                                public void accept(Apod apod) {
                                    //TODO mapper
                                    apodDao.insertAll(Collections.singletonList(new ApodEntity()));
                                }
                            });
                        } else {
                            return Single.error(throwable);
                        }
                    }
                });
    }

    @Override
    public Single<Apod> fetchApod(String date) {
        return repository.fetchApod(date);
    }
}

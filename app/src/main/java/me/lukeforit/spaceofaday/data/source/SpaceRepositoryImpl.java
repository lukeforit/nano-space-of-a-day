package me.lukeforit.spaceofaday.data.source;

import android.arch.persistence.room.EmptyResultSetException;

import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import me.lukeforit.spaceofaday.common.Utils;
import me.lukeforit.spaceofaday.data.mapper.ApodMapper;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.cache.ApodDao;
import me.lukeforit.spaceofaday.data.source.cache.ApodEntity;

public class SpaceRepositoryImpl implements SpaceRepository {

    private ApodRepository repository;
    private ApodDao apodDao;
    private ApodMapper mapper;

    public SpaceRepositoryImpl(ApodRepository repository, ApodDao apodDao, ApodMapper mapper) {
        this.repository = repository;
        this.apodDao = apodDao;
        this.mapper = mapper;
    }

    @Override
    public Single<List<Apod>> fetchAllApods() {
        return apodDao.fetchAll().map(new Function<List<ApodEntity>, List<Apod>>() {
            @Override
            public List<Apod> apply(List<ApodEntity> apodEntities) {
                return mapper.map(apodEntities);
            }
        });
    }

    @Override
    public Single<Apod> fetchApod(final String date) {
        return apodDao.fetchBy(Utils.INSTANCE.getDateAsInt(date))
                .map(new Function<ApodEntity, Apod>() {
                    @Override
                    public Apod apply(ApodEntity apodEntity) {
                        return mapper.map(apodEntity);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, SingleSource<? extends Apod>>() {
                    @Override
                    public SingleSource<? extends Apod> apply(Throwable throwable) {
                        if (throwable instanceof EmptyResultSetException) {
                            return repository.fetchApod(date).doOnSuccess(new Consumer<Apod>() {
                                @Override
                                public void accept(Apod apod) {
                                    apodDao.insertAll(Collections.singletonList(mapper.map(apod)));
                                }
                            });
                        } else {
                            return Single.error(throwable);
                        }
                    }
                });
    }
}

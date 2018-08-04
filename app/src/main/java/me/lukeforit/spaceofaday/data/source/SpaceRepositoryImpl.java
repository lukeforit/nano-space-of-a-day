package me.lukeforit.spaceofaday.data.source;

import java.util.List;

import io.reactivex.Single;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.cache.ApodDao;

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
        return repository.fetchApod();
    }

    @Override
    public Single<Apod> fetchApod(String date) {
        return repository.fetchApod(date);
    }
}

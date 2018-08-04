package me.lukeforit.spaceofaday.data.source;

import io.reactivex.Single;
import me.lukeforit.spaceofaday.data.model.Apod;

public interface ApodRepository {
    Single<Apod> fetchApod();
    Single<Apod> fetchApod(String date);
}

package me.lukeforit.spaceofaday.data.source;

import io.reactivex.Single;
import me.lukeforit.spaceofaday.data.model.Apod;

public interface SpaceRepository {
    Single<Apod> getApod();
}

package me.lukeforit.spaceofaday.data.source;

import io.reactivex.Observable;
import me.lukeforit.spaceofaday.data.model.Apod;

public interface SpaceRepository {
    Observable<Apod> getApod();
}

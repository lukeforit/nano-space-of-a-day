package me.lukeforit.spaceofaday.data.source;

import me.lukeforit.spaceofaday.data.model.Apod;

public interface SpaceRepository {
    Apod getApod();
}

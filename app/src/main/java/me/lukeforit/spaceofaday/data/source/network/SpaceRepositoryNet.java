package me.lukeforit.spaceofaday.data.source.network;

import java.io.IOException;

import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;

public class SpaceRepositoryNet implements SpaceRepository {

    private SpaceRestService service;

    public SpaceRepositoryNet(SpaceRestService service) {
        this.service = service;
    }

    @Override
    public Apod getApod() {
        try {
            return service.getData("").execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

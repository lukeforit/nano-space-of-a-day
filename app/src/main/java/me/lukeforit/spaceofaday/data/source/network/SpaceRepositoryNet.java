package me.lukeforit.spaceofaday.data.source.network;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;
import retrofit2.Response;

public class SpaceRepositoryNet implements SpaceRepository {

    private SpaceRestService service;

    public SpaceRepositoryNet(SpaceRestService service) {
        this.service = service;
    }

    @Override
    public Observable<Apod> getApod() {
        return service.getData("")
                .map(new Function<Response<Apod>, Apod>() {
                    @Override
                    public Apod apply(Response<Apod> apodResponse) {
                        return apodResponse.body();
                    }
                });
    }
}

package me.lukeforit.spaceofaday.data.source.network;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import me.lukeforit.spaceofaday.BuildConfig;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.ApodRepository;
import retrofit2.Response;

public class ApodRepositoryNet implements ApodRepository {

    private ApodRestService service;

    public ApodRepositoryNet(ApodRestService service) {
        this.service = service;
    }

    @Override
    public Single<Apod> fetchApod(String date) {
        return service.getData(BuildConfig.apiKey, date)
                .map(new Function<Response<Apod>, Apod>() {
                    @Override
                    public Apod apply(Response<Apod> apodResponse) {
                        return apodResponse.body();
                    }
                });
    }
}

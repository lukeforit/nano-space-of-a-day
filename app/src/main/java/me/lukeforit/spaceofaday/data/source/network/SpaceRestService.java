package me.lukeforit.spaceofaday.data.source.network;

import io.reactivex.Observable;
import me.lukeforit.spaceofaday.data.model.Apod;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpaceRestService {
    @GET("apod")
    Observable<Response<Apod>> getData(@Query("api_key") String apiKey);
}

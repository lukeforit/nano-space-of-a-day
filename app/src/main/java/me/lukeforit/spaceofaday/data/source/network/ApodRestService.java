package me.lukeforit.spaceofaday.data.source.network;

import io.reactivex.Single;
import me.lukeforit.spaceofaday.data.model.Apod;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApodRestService {
    @GET("apod")
    Single<Response<Apod>> getData(@Query("api_key") String apiKey);

    @GET("apod")
    Single<Response<Apod>> getData(@Query("api_key") String apiKey, @Query("date") String date);
}
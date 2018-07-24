package me.lukeforit.spaceofaday.data.source.network;

import me.lukeforit.spaceofaday.data.model.Apod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpaceRestService {
    @GET("apod")
    Call<Apod> getData(@Query("api_key") String apiKey);
}

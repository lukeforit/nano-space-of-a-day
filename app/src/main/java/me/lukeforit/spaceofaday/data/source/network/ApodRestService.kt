package me.lukeforit.spaceofaday.data.source.network

import io.reactivex.Single
import me.lukeforit.spaceofaday.data.model.Apod
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodRestService {
    @GET("apod")
    fun getData(@Query("api_key") apiKey: String): Single<Response<Apod>>

    @GET("apod")
    fun getData(@Query("api_key") apiKey: String, @Query("date") date: String): Single<Response<Apod>>
}

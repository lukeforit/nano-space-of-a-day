package me.lukeforit.spaceofaday.data.source.network

import io.reactivex.Single
import me.lukeforit.spaceofaday.BuildConfig
import me.lukeforit.spaceofaday.data.model.Apod
import me.lukeforit.spaceofaday.data.source.ApodRepository

class ApodRepositoryNet(private val service: ApodRestService) : ApodRepository {

    override fun fetchApod(date: String): Single<Apod> {
        return service.getData(BuildConfig.apiKey, date)
                .map { apodResponse -> apodResponse.body() }
    }
}

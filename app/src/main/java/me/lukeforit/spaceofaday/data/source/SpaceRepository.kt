package me.lukeforit.spaceofaday.data.source

import io.reactivex.Single
import me.lukeforit.spaceofaday.data.model.Apod

interface SpaceRepository : ApodRepository {
    fun fetchAllApods(): Single<List<Apod>>
}

package me.lukeforit.spaceofaday.data.source

import io.reactivex.Single
import me.lukeforit.spaceofaday.data.model.Apod

interface ApodRepository {
    fun fetchApod(date: String): Single<Apod>
}

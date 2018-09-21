package me.lukeforit.spaceofaday.data.source

import android.arch.persistence.room.EmptyResultSetException

import java.util.Collections

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.data.mapper.ApodMapper
import me.lukeforit.spaceofaday.data.model.Apod
import me.lukeforit.spaceofaday.data.source.cache.ApodDao
import me.lukeforit.spaceofaday.data.source.cache.ApodEntity

class SpaceRepositoryImpl(private val repository: ApodRepository, private val apodDao: ApodDao, private val mapper: ApodMapper) : SpaceRepository {

    override fun fetchAllApods(): Single<List<Apod>> {
        return apodDao.fetchAll().map { apodEntities -> mapper.map(apodEntities) }
    }

    override fun fetchApod(date: String): Single<Apod> {
        return apodDao.fetchBy(Utils.getDateAsInt(date))
                .map { apodEntity -> mapper.map(apodEntity) }
                .onErrorResumeNext { throwable ->
                    if (throwable is EmptyResultSetException) {
                        repository.fetchApod(date).doOnSuccess { apod -> apodDao.insertAll(listOf(mapper.map(apod))) }
                    } else {
                        Single.error(throwable)
                    }
                }
    }
}

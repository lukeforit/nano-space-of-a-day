package me.lukeforit.spaceofaday.data.source

import androidx.room.EmptyResultSetException
import io.reactivex.Single
import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.data.mapper.ApodMapper
import me.lukeforit.spaceofaday.data.model.Apod
import me.lukeforit.spaceofaday.data.source.cache.ApodDao

class SpaceRepositoryImpl(private val repository: ApodRepository, private val apodDao: ApodDao, private val mapper: ApodMapper) : SpaceRepository {

    override fun fetchAllApods(): Single<List<Apod>> {
        return apodDao.fetchAll().map { it.map(mapper::map) }
    }

    override fun fetchApod(date: String): Single<Apod> {
        return apodDao.fetchBy(Utils.getDateAsInt(date))
                .map(mapper::map)
                .onErrorResumeNext { throwable ->
                    if (throwable is EmptyResultSetException) {
                        repository.fetchApod(date).doOnSuccess { apod -> apodDao.insertAll(listOf(mapper.map(apod))) }
                    } else {
                        Single.error(throwable)
                    }
                }
    }
}

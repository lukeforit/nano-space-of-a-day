package me.lukeforit.spaceofaday.data.source.cache

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

import io.reactivex.Single

internal const val TABLE_NAME = "apod"

@Dao
interface ApodDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun fetchAll(): Single<List<ApodEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(apods: List<ApodEntity>)

    @Query("select * from $TABLE_NAME where id = :id")
    fun fetchBy(id: Int): Single<ApodEntity>

    @Query("select * from $TABLE_NAME where id = :id")
    fun fetchSynchronyouslyBy(id: Int): ApodEntity
}

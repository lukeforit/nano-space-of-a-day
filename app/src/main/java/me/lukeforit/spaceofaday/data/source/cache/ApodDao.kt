package me.lukeforit.spaceofaday.data.source.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

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

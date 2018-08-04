package me.lukeforit.spaceofaday.data.source.cache;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ApodDao {
    String TABLE_NAME = "apod";

    @Query("SELECT * FROM " + TABLE_NAME)
    Single<List<ApodEntity>> fetchAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ApodEntity> apods);

    @Query("select * from " + TABLE_NAME + " where id = :id")
    Single<ApodEntity> fetchBy(int id);
}

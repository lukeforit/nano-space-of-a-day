package me.lukeforit.spaceofaday.data.source.cache;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ApodDao {
    String TABLE_NAME = "apod";

    @Query("SELECT * FROM " + TABLE_NAME)
    LiveData<List<ApodEntity>> fetchAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<ApodEntity> apods);

    @Query("select * from " + TABLE_NAME + " where id = :id")
    LiveData<ApodEntity> fetchBy(int id);
}

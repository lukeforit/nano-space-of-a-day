package me.lukeforit.spaceofaday.data.source.cache;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {ApodEntity.class}, version = 1)
public abstract class SpaceDatabase extends RoomDatabase {
    public static String DATABASE_NAME = "space-db";

    public abstract ApodDao apodDao();
}

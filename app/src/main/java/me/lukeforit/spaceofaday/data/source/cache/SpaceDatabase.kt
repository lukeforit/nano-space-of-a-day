package me.lukeforit.spaceofaday.data.source.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(ApodEntity::class), version = 1)
abstract class SpaceDatabase : RoomDatabase() {

    abstract fun apodDao(): ApodDao

    companion object {
        var DATABASE_NAME = "space-db"
    }
}

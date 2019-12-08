package me.lukeforit.spaceofaday.data.source.cache

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_NAME = "space-db"

@Database(entities = arrayOf(ApodEntity::class), version = 1)
abstract class SpaceDatabase : RoomDatabase() {

    abstract fun apodDao(): ApodDao
}

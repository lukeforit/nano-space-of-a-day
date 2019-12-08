package me.lukeforit.spaceofaday.data.source.cache

import androidx.room.Room
import android.content.Context

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class CacheModule {
    @Provides
    @Singleton
    fun provideSpaceDatabase(@Named("appContext") context: Context): SpaceDatabase {
        return Room.databaseBuilder(context, SpaceDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideApodDao(spaceDatabase: SpaceDatabase): ApodDao {
        return spaceDatabase.apodDao()
    }
}

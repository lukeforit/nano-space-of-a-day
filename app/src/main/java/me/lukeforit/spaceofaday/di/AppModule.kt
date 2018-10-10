package me.lukeforit.spaceofaday.di

import android.content.Context
import dagger.Module
import dagger.Provides
import me.lukeforit.spaceofaday.common.SpaceApp
import me.lukeforit.spaceofaday.data.mapper.ApodMapper
import me.lukeforit.spaceofaday.data.source.ApodRepository
import me.lukeforit.spaceofaday.data.source.SpaceRepository
import me.lukeforit.spaceofaday.data.source.SpaceRepositoryImpl
import me.lukeforit.spaceofaday.data.source.cache.ApodDao
import me.lukeforit.spaceofaday.data.source.cache.CacheModule
import me.lukeforit.spaceofaday.data.source.network.NetModule
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [
    NetModule::class,
    CacheModule::class,
    ViewModelModule::class,
    AnalyticsModule::class
])
class AppModule {
    @Provides
    @Singleton
    @Named("appContext")
    fun provideContext(app: SpaceApp): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideSpaceRepository(apodRepository: ApodRepository, apodDao: ApodDao, mapper: ApodMapper): SpaceRepository {
        return SpaceRepositoryImpl(apodRepository, apodDao, mapper)
    }
}

package me.lukeforit.spaceofaday.di;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.lukeforit.spaceofaday.common.SpaceApp;
import me.lukeforit.spaceofaday.data.source.ApodRepository;
import me.lukeforit.spaceofaday.data.source.SpaceRepository;
import me.lukeforit.spaceofaday.data.source.SpaceRepositoryImpl;
import me.lukeforit.spaceofaday.data.source.cache.ApodDao;
import me.lukeforit.spaceofaday.data.source.cache.CacheModule;
import me.lukeforit.spaceofaday.data.source.network.NetModule;

@Module(includes = {
        NetModule.class,
        CacheModule.class,
        ViewModelModule.class
})
public class AppModule {
    @Provides
    @Singleton
    @Named("appContext")
    public Context provideContext(SpaceApp app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    public SpaceRepository provideSpaceRepository(ApodRepository apodRepository, ApodDao apodDao) {
        return new SpaceRepositoryImpl(apodRepository, apodDao);
    }
}

package me.lukeforit.spaceofaday.data.source.cache;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {
    @Provides
    @Singleton
    public SpaceDatabase provideSpaceDatabase(@NonNull @Named("appContext") Context context) {
        return Room.databaseBuilder(context, SpaceDatabase.class, SpaceDatabase.DATABASE_NAME).build();
    }

    @Provides
    @Singleton
    public ApodDao provideApodDao(@NonNull SpaceDatabase spaceDatabase) {
        return spaceDatabase.apodDao();
    }
}

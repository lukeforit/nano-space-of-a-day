package me.lukeforit.spaceofaday.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.lukeforit.spaceofaday.common.SpaceApp;
import me.lukeforit.spaceofaday.data.source.cache.SpaceDatabase;

@Module(includes = {
        NetModule.class,
        ViewModelModule.class
})
public class AppModule {
    @Provides
    @Singleton
    public Context provideContext(SpaceApp app) {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    public SpaceDatabase provideSpaceDatabase(Context context) {
        return Room.databaseBuilder(context, SpaceDatabase.class, SpaceDatabase.DATABASE_NAME).build();
    }
}

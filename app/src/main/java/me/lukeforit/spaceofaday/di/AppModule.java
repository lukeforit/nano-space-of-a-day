package me.lukeforit.spaceofaday.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.lukeforit.spaceofaday.common.SpaceApp;

@Module(includes = NetModule.class)
public class AppModule {
    @Provides
    @Singleton
    public Context provideContext(SpaceApp app) {
        return app.getApplicationContext();
    }
}

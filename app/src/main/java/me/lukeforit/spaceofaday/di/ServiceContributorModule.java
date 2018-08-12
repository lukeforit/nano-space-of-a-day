package me.lukeforit.spaceofaday.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.lukeforit.spaceofaday.ui.widget.ApodIntentService;

@Module
public abstract class ServiceContributorModule {
    @ContributesAndroidInjector
    abstract ApodIntentService provideApodIntentService();
}

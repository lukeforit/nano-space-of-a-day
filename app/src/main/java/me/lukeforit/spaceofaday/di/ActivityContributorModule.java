package me.lukeforit.spaceofaday.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.lukeforit.spaceofaday.ui.home.HomeActivity;

@Module
public abstract class ActivityContributorModule {

    @ContributesAndroidInjector
    abstract HomeActivity contributeHomeActivity();
}

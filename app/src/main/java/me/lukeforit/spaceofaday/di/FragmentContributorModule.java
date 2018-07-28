package me.lukeforit.spaceofaday.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.lukeforit.spaceofaday.ui.archive.ApodArchiveFragment;
import me.lukeforit.spaceofaday.ui.pod.ApodDetailsFragment;

@Module
public abstract class FragmentContributorModule {
    @ContributesAndroidInjector
    abstract ApodArchiveFragment contributeApodArchiveFragment();

    @ContributesAndroidInjector
    abstract ApodDetailsFragment contributeApodDetailsFragment();
}

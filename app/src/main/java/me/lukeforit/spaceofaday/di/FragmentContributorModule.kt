package me.lukeforit.spaceofaday.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.lukeforit.spaceofaday.ui.archive.ApodArchiveFragment
import me.lukeforit.spaceofaday.ui.pod.ApodDetailsFragment

@Module
abstract class FragmentContributorModule {
    @ContributesAndroidInjector
    internal abstract fun contributeApodArchiveFragment(): ApodArchiveFragment

    @ContributesAndroidInjector
    internal abstract fun contributeApodDetailsFragment(): ApodDetailsFragment
}

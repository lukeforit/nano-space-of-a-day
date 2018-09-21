package me.lukeforit.spaceofaday.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.lukeforit.spaceofaday.ui.home.HomeActivity
import me.lukeforit.spaceofaday.ui.widget.config.ApodWidgetConfigureActivity

@Module
abstract class ActivityContributorModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun contributeApodWidgetConfigureActivity(): ApodWidgetConfigureActivity
}

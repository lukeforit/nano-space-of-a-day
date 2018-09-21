package me.lukeforit.spaceofaday.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.lukeforit.spaceofaday.service.FetchApodFirebaseJobService
import me.lukeforit.spaceofaday.ui.widget.ApodIntentService

@Module
abstract class ServiceContributorModule {
    @ContributesAndroidInjector
    internal abstract fun provideApodIntentService(): ApodIntentService

    @ContributesAndroidInjector
    internal abstract fun provideApodFirebaseJobService(): FetchApodFirebaseJobService
}

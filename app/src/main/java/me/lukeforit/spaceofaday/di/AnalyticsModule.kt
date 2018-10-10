package me.lukeforit.spaceofaday.di

import android.content.Context
import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker
import dagger.Module
import dagger.Provides
import me.lukeforit.spaceofaday.R
import javax.inject.Named
import javax.inject.Singleton

@Module
class AnalyticsModule {

    @Provides
    @Singleton
    fun provideGoogleAnalytics(@Named("appContext") context: Context): GoogleAnalytics =
            GoogleAnalytics.getInstance(context)

    @Provides
    @Singleton
    fun provideTracker(googleAnalytics: GoogleAnalytics): Tracker =
            googleAnalytics.newTracker(R.xml.global_tracker)
}
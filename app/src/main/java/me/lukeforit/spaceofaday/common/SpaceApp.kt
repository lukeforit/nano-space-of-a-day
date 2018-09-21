package me.lukeforit.spaceofaday.common

import android.app.Activity
import android.app.Application
import android.app.Service

import com.google.android.gms.analytics.GoogleAnalytics
import com.google.android.gms.analytics.Tracker

import javax.inject.Inject

import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.di.DaggerAppComponent

class SpaceApp : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    val defaultTracker: Tracker
        @Synchronized get() {
            if (tracker == null) {
                tracker = analytics!!.newTracker(R.xml.global_tracker)
            }
            return tracker!!
        }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().appModule(this).build().inject(this)

        analytics = GoogleAnalytics.getInstance(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service>? {
        return serviceInjector
    }

    companion object {

        private var analytics: GoogleAnalytics? = null
        private var tracker: Tracker? = null
    }
}

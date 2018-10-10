package me.lukeforit.spaceofaday.common

import android.app.Activity
import android.app.Application
import android.app.Service
import com.google.android.gms.analytics.Tracker
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import me.lukeforit.spaceofaday.di.DaggerAppComponent
import javax.inject.Inject

class SpaceApp : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var defaultTracker: Tracker
        @Synchronized
        get

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .appModule(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

    override fun serviceInjector(): AndroidInjector<Service>? {
        return serviceInjector
    }
}

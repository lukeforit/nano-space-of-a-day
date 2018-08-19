package me.lukeforit.spaceofaday.common;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.di.DaggerAppComponent;

public class SpaceApp extends Application implements HasActivityInjector, HasServiceInjector {

    private static GoogleAnalytics analytics;
    private static Tracker tracker;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<Service> serviceInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().appModule(this).build().inject(this);

        analytics = GoogleAnalytics.getInstance(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceInjector;
    }

    synchronized public Tracker getDefaultTracker() {
        if (tracker == null) {
            tracker = analytics.newTracker(R.xml.global_tracker);
        }

        return tracker;
    }
}

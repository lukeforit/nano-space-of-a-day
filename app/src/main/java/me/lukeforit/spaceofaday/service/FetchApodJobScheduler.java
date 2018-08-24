package me.lukeforit.spaceofaday.service;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

public class FetchApodJobScheduler {

    private static final String JOB_TAG = "FetchApodJobTag";
    private static boolean initialized;

    synchronized public static void scheduleFetchingApod(@NonNull final Context context) {
        if (initialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        boolean autoSync = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getBoolean(("auto_sync"), true);

        Job constraintReminderJob = dispatcher.newJobBuilder()
                .setService(FetchApodFirebaseJobService.class)
                .setTag(JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(autoSync)
                .setTrigger(Trigger.executionWindow(
                        (int) TimeUnit.DAYS.toSeconds(1),
                        (int) TimeUnit.DAYS.toSeconds(2)))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(constraintReminderJob);
        initialized = true;
    }
}

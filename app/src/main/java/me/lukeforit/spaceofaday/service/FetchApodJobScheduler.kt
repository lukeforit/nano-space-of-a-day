package me.lukeforit.spaceofaday.service

import android.content.Context
import android.preference.PreferenceManager

import com.firebase.jobdispatcher.Constraint
import com.firebase.jobdispatcher.Driver
import com.firebase.jobdispatcher.FirebaseJobDispatcher
import com.firebase.jobdispatcher.GooglePlayDriver
import com.firebase.jobdispatcher.Job
import com.firebase.jobdispatcher.Lifetime
import com.firebase.jobdispatcher.Trigger

import java.util.concurrent.TimeUnit

object FetchApodJobScheduler {

    private val JOB_TAG = "FetchApodJobTag"
    private var initialized: Boolean = false

    @Synchronized
    fun scheduleFetchingApod(context: Context) {
        if (initialized) return

        val driver = GooglePlayDriver(context)
        val dispatcher = FirebaseJobDispatcher(driver)

        val autoSync = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getBoolean("auto_sync", true)

        val constraintReminderJob = dispatcher.newJobBuilder()
                .setService(FetchApodFirebaseJobService::class.java)
                .setTag(JOB_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(autoSync)
                .setTrigger(Trigger.executionWindow(
                        TimeUnit.DAYS.toSeconds(1).toInt(),
                        TimeUnit.DAYS.toSeconds(2).toInt()))
                .setReplaceCurrent(true)
                .build()

        dispatcher.schedule(constraintReminderJob)
        initialized = true
    }
}

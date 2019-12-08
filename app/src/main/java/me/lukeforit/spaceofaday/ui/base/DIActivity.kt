package me.lukeforit.spaceofaday.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity

import com.google.android.gms.analytics.HitBuilders
import com.google.android.gms.analytics.Tracker

import javax.inject.Inject

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.lukeforit.spaceofaday.common.SpaceApp

abstract class DIActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private var tracker: Tracker? = null

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val application = application as SpaceApp
        tracker = application.defaultTracker
    }

    override fun onResume() {
        super.onResume()
        tracker!!.setScreenName("Activity-" + this.javaClass.simpleName)
        tracker!!.send(HitBuilders.ScreenViewBuilder().build())
    }

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment>? {
        return fragmentDispatchingAndroidInjector
    }
}

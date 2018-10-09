package me.lukeforit.spaceofaday.ui.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.service.FetchApodJobScheduler
import me.lukeforit.spaceofaday.ui.archive.ApodArchiveFragment
import me.lukeforit.spaceofaday.ui.base.DIActivity
import me.lukeforit.spaceofaday.ui.base.DIViewModelFactory
import me.lukeforit.spaceofaday.ui.pod.ApodDetailsFragment
import me.lukeforit.spaceofaday.ui.pref.SettingsFragment
import javax.inject.Inject

class HomeActivity : DIActivity() {

    private var viewModel: HomeViewModel? = null

    @Inject
    internal lateinit var viewModelFactory: DIViewModelFactory

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragmentManager = supportFragmentManager
        for (i in 0 until fragmentManager.backStackEntryCount) {
            fragmentManager.popBackStack()
        }
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, ApodDetailsFragment.newInstance(""))
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_archive -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, ApodArchiveFragment.newInstance())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, SettingsFragment())
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel!!.openApodScreen.observe(this, Observer { stringEvent ->
            if (stringEvent != null && !stringEvent.isDelivered) {
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, ApodDetailsFragment.newInstance(stringEvent.deliverData() ?: ""))
                        .addToBackStack(null)
                        .commit()
            }
        })

        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content, ApodDetailsFragment.newInstance(""))
                    .commit()
        }

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713")

        val adView = findViewById<AdView>(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        FetchApodJobScheduler.scheduleFetchingApod(this)
    }

}

package me.lukeforit.spaceofaday.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.service.FetchApodJobScheduler;
import me.lukeforit.spaceofaday.ui.archive.ApodArchiveFragment;
import me.lukeforit.spaceofaday.ui.base.DIActivity;
import me.lukeforit.spaceofaday.ui.pod.ApodDetailsFragment;
import me.lukeforit.spaceofaday.ui.pref.SettingsFragment;

public class HomeActivity extends DIActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, ApodDetailsFragment.newInstance(""))
                            .commit();
                    return true;
                case R.id.navigation_archive:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, ApodArchiveFragment.newInstance())
                            .commit();
                    return true;
                case R.id.navigation_settings:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, new SettingsFragment())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, ApodDetailsFragment.newInstance(""))
                    .commit();
        }

        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        AdView adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        FetchApodJobScheduler.scheduleFetchingApod(this);
    }

}

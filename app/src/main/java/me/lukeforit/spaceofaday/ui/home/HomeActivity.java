package me.lukeforit.spaceofaday.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.TextView;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.ui.archive.ApodArchiveFragment;
import me.lukeforit.spaceofaday.ui.base.DIActivity;
import me.lukeforit.spaceofaday.ui.pod.ApodDetailsFragment;
import me.lukeforit.spaceofaday.ui.pref.SettingsActivity;

public class HomeActivity extends DIActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, ApodDetailsFragment.newInstance("", ""))
                            .commit();
                    return true;
                case R.id.navigation_archive:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, ApodArchiveFragment.newInstance(1))
                            .commit();
                    return true;
                case R.id.navigation_settings:
                    Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                    startActivity(intent);
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

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, ApodDetailsFragment.newInstance("", ""))
                .commit();
    }

}

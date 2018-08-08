package me.lukeforit.spaceofaday.ui.widget.config;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.databinding.ActivityApodWidgetConfigureBinding;
import me.lukeforit.spaceofaday.ui.base.DIActivity;
import me.lukeforit.spaceofaday.ui.base.DIViewModelFactory;
import me.lukeforit.spaceofaday.ui.widget.ApodWidget;

public class ApodWidgetConfigureActivity extends DIActivity {

    private static final String PREFS_NAME = "me.lukeforit.spaceofaday.ui.widget.ApodWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";

    int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Inject
    protected DIViewModelFactory viewModelFactory;
    ApodWidgetConfigureViewModel viewModel;

    public static void saveTitlePref(Context context, int appWidgetId, int option) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putInt(PREF_PREFIX_KEY + appWidgetId, option);
        prefs.apply();
    }

    public static int loadOptionPref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getInt(PREF_PREFIX_KEY + appWidgetId, R.id.radio_both);
    }

    public static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ApodWidgetConfigureViewModel.class);

        ActivityApodWidgetConfigureBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_apod_widget_configure);
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        binding.optionsRg.check(loadOptionPref(this, appWidgetId));
    }

    public void onRadioButtonClicked(View view) {
        saveTitlePref(this, appWidgetId, view.getId());

        // It is the responsibility of the configuration activity to update the app widget
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        ApodWidget.updateAppWidget(this, appWidgetManager, appWidgetId);

        // Make sure we pass back the original appWidgetId
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}


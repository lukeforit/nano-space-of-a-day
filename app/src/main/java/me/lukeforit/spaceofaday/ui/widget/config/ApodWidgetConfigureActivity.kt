package me.lukeforit.spaceofaday.ui.widget.config

import android.app.Activity
import android.appwidget.AppWidgetManager
import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.databinding.ActivityApodWidgetConfigureBinding
import me.lukeforit.spaceofaday.ui.base.DIActivity
import me.lukeforit.spaceofaday.ui.base.DIViewModelFactory
import javax.inject.Inject

class ApodWidgetConfigureActivity : DIActivity() {

    internal var appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID

    @Inject
    lateinit var viewModelFactory: DIViewModelFactory
    internal lateinit var viewModel: ApodWidgetConfigureViewModel

    public override fun onCreate(icicle: Bundle?) {
        super.onCreate(icicle)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ApodWidgetConfigureViewModel::class.java)

        val binding = DataBindingUtil.setContentView<ActivityApodWidgetConfigureBinding>(this, R.layout.activity_apod_widget_configure)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(Activity.RESULT_CANCELED)

        // Find the widget id from the intent.
        val intent = intent
        val extras = intent.extras
        if (extras != null) {
            appWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()
            return
        }

        binding.optionsRg.check(loadOptionPref(this, appWidgetId))
    }

    fun onRadioButtonClicked(view: View) {
        saveTitlePref(this, appWidgetId, view.id)

        // It is the responsibility of the configuration activity to update the app widget
        val appWidgetManager = AppWidgetManager.getInstance(this)
        //        ApodWidget.updateAppWidget(this, appWidgetManager, appWidgetId);

        // Make sure we pass back the original appWidgetId
        val resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }

    companion object {

        private val PREFS_NAME = "me.lukeforit.spaceofaday.ui.widget.ApodWidget"
        private val PREF_PREFIX_KEY = "appwidget_"

        fun saveTitlePref(context: Context, appWidgetId: Int, option: Int) {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
            prefs.putInt(PREF_PREFIX_KEY + appWidgetId, option)
            prefs.apply()
        }

        fun loadOptionPref(context: Context, appWidgetId: Int): Int {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0)
            return prefs.getInt(PREF_PREFIX_KEY + appWidgetId, R.id.radio_both)
        }

        fun deleteTitlePref(context: Context, appWidgetId: Int) {
            val prefs = context.getSharedPreferences(PREFS_NAME, 0).edit()
            prefs.remove(PREF_PREFIX_KEY + appWidgetId)
            prefs.apply()
        }
    }
}


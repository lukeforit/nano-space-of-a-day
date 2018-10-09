package me.lukeforit.spaceofaday.ui.widget

import android.app.IntentService
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import dagger.android.AndroidInjection
import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.data.mapper.ApodMapper
import me.lukeforit.spaceofaday.data.source.cache.ApodDao
import javax.inject.Inject

class ApodIntentService : IntentService("ApodIntentService") {

    @Inject
    internal lateinit var apodDao: ApodDao
    @Inject
    internal lateinit var mapper: ApodMapper

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null && ACTION_FETCH_APOD == intent.action) {
            handleActionFetchApod(intent.getStringExtra(EXTRA_DATE))
        }
    }

    private fun handleActionFetchApod(date: String) {
        val apodEntity = apodDao!!.fetchSynchronyouslyBy(Utils.getDateAsInt(date))
                ?: //TODO handle case
                return

        val apod = mapper!!.map(apodEntity)
        val appWidgetManager = AppWidgetManager.getInstance(this)
        val appWidgetIds = appWidgetManager.getAppWidgetIds(ComponentName(this, ApodWidget::class.java))

        for (appWidgetId in appWidgetIds) {
            ApodWidget.updateAppWidget(this, appWidgetManager, appWidgetId, apod)
        }
    }

    companion object {

        private val ACTION_FETCH_APOD = "me.lukeforit.spaceofaday.ui.widget.action.FETCH_APOD"
        private val EXTRA_DATE = "me.lukeforit.spaceofaday.ui.widget.extra.DATE"

        fun startActionFetchApod(context: Context, date: String) {
            val intent = Intent(context, ApodIntentService::class.java)
            intent.action = ACTION_FETCH_APOD
            intent.putExtra(EXTRA_DATE, date)
            context.startService(intent)
        }
    }
}

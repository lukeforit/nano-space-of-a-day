package me.lukeforit.spaceofaday.ui.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.widget.RemoteViews

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import java.util.concurrent.ExecutionException

import me.lukeforit.spaceofaday.R
import me.lukeforit.spaceofaday.common.Utils
import me.lukeforit.spaceofaday.data.model.Apod
import me.lukeforit.spaceofaday.service.FetchApodJobScheduler
import me.lukeforit.spaceofaday.ui.widget.config.ApodWidgetConfigureActivity

class ApodWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        //TODO handle IllegalStateException: Not allowed to start service Intent - job
        ApodIntentService.startActionFetchApod(context, Utils.defaultDateAsString)
    }

    override fun onDeleted(context: Context, appWidgetIds: IntArray) {
        for (appWidgetId in appWidgetIds) {
            ApodWidgetConfigureActivity.deleteTitlePref(context, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        FetchApodJobScheduler.scheduleFetchingApod(context)
    }

    override fun onDisabled(context: Context) {}

    companion object {

        fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                            appWidgetId: Int, apod: Apod) {

            val pref = ApodWidgetConfigureActivity.loadOptionPref(context, appWidgetId)

            val views = RemoteViews(context.packageName, R.layout.widget_apod)

            views.setTextViewText(R.id.title_tv, apod.title)

            if (pref == R.id.radio_both || pref == R.id.radio_info) {
                views.setTextViewText(R.id.explanation_tv, apod.explanation)
            }

            if (pref == R.id.radio_both || pref == R.id.radio_picture) {
                var bitmap: Bitmap? = null
                try {
                    bitmap = Glide
                            .with(context.applicationContext)
                            .asBitmap()
                            .apply(RequestOptions().centerCrop())
                            .load(apod.url)
                            .submit().get()
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } catch (e: ExecutionException) {
                    e.printStackTrace()
                }

                views.setBitmap(R.id.picture_iv, "setImageBitmap", bitmap)
            }

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

        fun triggerWidgetUpdate(context: Context) {
            val intent = Intent(context, ApodWidget::class.java)
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE

            val ids = AppWidgetManager.getInstance(context)
                    .getAppWidgetIds(ComponentName(context, ApodWidget::class.java))
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            context.sendBroadcast(intent)
        }
    }
}


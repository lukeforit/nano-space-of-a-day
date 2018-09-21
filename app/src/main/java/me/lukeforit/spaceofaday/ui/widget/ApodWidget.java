package me.lukeforit.spaceofaday.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.concurrent.ExecutionException;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.common.Utils;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.service.FetchApodJobScheduler;
import me.lukeforit.spaceofaday.ui.widget.config.ApodWidgetConfigureActivity;

public class ApodWidget extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, Apod apod) {

        int pref = ApodWidgetConfigureActivity.loadOptionPref(context, appWidgetId);

        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_apod);

        views.setTextViewText(R.id.title_tv, apod.getTitle());

        if (pref == R.id.radio_both || pref == R.id.radio_info) {
            views.setTextViewText(R.id.explanation_tv, apod.getExplanation());
        }

        if (pref == R.id.radio_both || pref == R.id.radio_picture) {
            Bitmap bitmap = null;
            try {
                bitmap = Glide
                        .with(context.getApplicationContext())
                        .asBitmap()
                        .apply(new RequestOptions().centerCrop())
                        .load(apod.getUrl())
                        .submit().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            views.setBitmap(R.id.picture_iv, "setImageBitmap", bitmap);
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void triggerWidgetUpdate(Context context) {
        Intent intent = new Intent(context, ApodWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, ApodWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //TODO handle IllegalStateException: Not allowed to start service Intent - job
        ApodIntentService.startActionFetchApod(context, Utils.INSTANCE.getDefaultDateAsString());
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            ApodWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        FetchApodJobScheduler.scheduleFetchingApod(context);
    }

    @Override
    public void onDisabled(Context context) {
    }
}


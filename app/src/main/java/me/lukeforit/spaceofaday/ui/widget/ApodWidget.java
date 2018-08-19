package me.lukeforit.spaceofaday.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.concurrent.ExecutionException;

import me.lukeforit.spaceofaday.R;
import me.lukeforit.spaceofaday.common.Utils;
import me.lukeforit.spaceofaday.service.FetchApodJobScheduler;
import me.lukeforit.spaceofaday.ui.widget.config.ApodWidgetConfigureActivity;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link ApodWidgetConfigureActivity ApodWidgetConfigureActivity}
 */
public class ApodWidget extends AppWidgetProvider {

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                       int appWidgetId, String imgUrl, String explanation) {

        int pref = ApodWidgetConfigureActivity.loadOptionPref(context, appWidgetId);

        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_apod);
        views.setTextViewText(R.id.explanation_tv, explanation);

        Bitmap b = null;
        try {
            b = Glide
                    .with(context.getApplicationContext())
                    .asBitmap()
                    .apply(new RequestOptions().centerCrop())
                    .load(imgUrl)
                    .submit().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        views.setBitmap(R.id.picture_iv, "setImageBitmap", b);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //TODO schedule job
        ApodIntentService.startActionFetchApod(context, Utils.getDefaultDateAsString());
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


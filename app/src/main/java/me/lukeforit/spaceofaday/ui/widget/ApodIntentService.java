package me.lukeforit.spaceofaday.ui.widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import me.lukeforit.spaceofaday.common.Utils;
import me.lukeforit.spaceofaday.data.mapper.ApodMapper;
import me.lukeforit.spaceofaday.data.model.Apod;
import me.lukeforit.spaceofaday.data.source.cache.ApodDao;
import me.lukeforit.spaceofaday.data.source.cache.ApodEntity;

public class ApodIntentService extends IntentService {

    private static final String ACTION_FETCH_APOD = "me.lukeforit.spaceofaday.ui.widget.action.FETCH_APOD";
    private static final String EXTRA_DATE = "me.lukeforit.spaceofaday.ui.widget.extra.DATE";

    @Inject
    ApodDao apodDao;
    @Inject
    ApodMapper mapper;

    public ApodIntentService() {
        super("ApodIntentService");
    }

    public static void startActionFetchApod(Context context, String date) {
        Intent intent = new Intent(context, ApodIntentService.class);
        intent.setAction(ACTION_FETCH_APOD);
        intent.putExtra(EXTRA_DATE, date);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        AndroidInjection.inject(this);
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null && ACTION_FETCH_APOD.equals(intent.getAction())) {
            handleActionFetchApod(intent.getStringExtra(EXTRA_DATE));
        }
    }

    private void handleActionFetchApod(String date) {
        ApodEntity apodEntity = apodDao.fetchSynchronyouslyBy(Utils.INSTANCE.getDateAsInt(date));

        if (apodEntity == null) {
            //TODO handle case
            return;
        }

        Apod apod = mapper.map(apodEntity);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, ApodWidget.class));

        for (int appWidgetId : appWidgetIds) {
            ApodWidget.updateAppWidget(this, appWidgetManager, appWidgetId, apod);
        }
    }
}

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

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
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

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
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

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFetchApod(String date) {
        ApodEntity apodEntity = apodDao.fetchSynchronyouslyBy(Utils.getDateAsInt(date));

        if (apodEntity == null) {
            //TODO handle case
            return;
        }

        Apod apod = mapper.map(apodEntity);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, ApodWidget.class));

        for (int appWidgetId : appWidgetIds) {
            ApodWidget.updateAppWidget(this, appWidgetManager, appWidgetId, apod.getUrl(), apod.getExplanation());
        }
    }
}

package mx.saudade.discovermusicapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import mx.saudade.discovermusicapp.data.AppCursorHelper;

/**
 * Created by angie on 7/4/16.
 */
public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        AppCursorHelper cursorHelper = new AppCursorHelper(context);
        int favoritesNumber = cursorHelper.getTracks().size();

        ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        for (int widgetId : allWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.widget_layout);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, createUpdateIntent(context, appWidgetIds), PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setTextViewText(R.id.widget_favorites, String.valueOf(favoritesNumber));
            remoteViews.setOnClickPendingIntent(R.id.widget_favorites, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

    private Intent createUpdateIntent(Context context, int[] appWidgetIds ) {
        Intent intent = new Intent(context, WidgetProvider.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        return intent;
    }
}

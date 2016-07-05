package mx.saudade.discovermusicapp.utils;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;

import mx.saudade.discovermusicapp.AnalyticsApplication;
import mx.saudade.discovermusicapp.R;

/**
 * Created by angie on 7/3/16.
 */
public final class NavigationUtils {

    private static final String TAG = NavigationUtils.class.getSimpleName();

    public static void loadActivity(AppCompatActivity activity, Class clz) {
        Intent i = new Intent();
        i.setClass(activity, clz);
        activity.startActivity(i);
    }

    public static void loadActivity(AppCompatActivity activity, Class clz, String extra_key
            , Parcelable extra) {
        Intent intent = new Intent();
        intent.setClass(activity, clz);
        intent.putExtra(extra_key, extra);
        activity.startActivity(intent);
    }

    public static void loadFragment(AppCompatActivity activity, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.activity_fragment_container, fragment)
                .commit();
    }

    public static void share(Application application, ShareActionProvider provider, String message) {
        if (message == null || provider == null) {
            return;
        } else {
            Log.e(TAG, "Error" + provider.toString() + " " + message);
        }

        Intent i = new Intent(Intent.ACTION_SEND);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT, message);

        provider.setShareIntent(i);
        ((AnalyticsApplication) application).trackEvent(
                application.getString(R.string.track),
                application.getString(R.string.share),
                message);
    }

}

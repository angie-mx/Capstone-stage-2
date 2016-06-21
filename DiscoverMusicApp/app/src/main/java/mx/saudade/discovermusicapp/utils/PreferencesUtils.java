package mx.saudade.discovermusicapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import org.apache.commons.lang3.StringUtils;

import mx.saudade.discovermusicapp.BuildConfig;

/**
 * Created by angie on 6/20/16.
 */
public final class PreferencesUtils {

    private static final String PREFERENCE_FILE_KEY = BuildConfig.APPLICATION_ID
            + "_PREFERENCE_FILE_KEY";

    private static final String POPULARITY_MIN_KEY = "PopularityMinKey";

    private static final String POPULARITY_MAX_KEY = "PopularityMaxKey";

    private static final String RESULTS_NUMBER_KEY = "ResultsNumberKey";

    private static final String LOCATION_KEY = "LocationKey";

    private PreferencesUtils() { }

    public static int getMinPopularity(Context context) {
        return getInt(context, POPULARITY_MIN_KEY);
    }

    public static void setMinPopularity(Context context, int min) {
        setInt(context, POPULARITY_MIN_KEY, min);
    }

    public static int getMaxPopularity(Context context) {
        return getInt(context, POPULARITY_MAX_KEY);
    }

    public static void setMaxPopularity(Context context, int max) {
        setInt(context, POPULARITY_MAX_KEY, max);
    }

    public static int getResultsNumber(Context context) {
        return getInt(context, RESULTS_NUMBER_KEY);
    }

    public static void setResultsNumber(Context context, int resultsNumber) {
        setInt(context, RESULTS_NUMBER_KEY, resultsNumber);
    }

    public static int getLocation(Context context) {
        return getInt(context, LOCATION_KEY);
    }

    public static void setLocation(Context context, String location) {
        setString(context, LOCATION_KEY, location);
    }

    private static int getInt(Context context, String key) {
        return getSharedPreferences(context).getInt(key, 0);
    }

    private static void setInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    private static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, StringUtils.EMPTY);
    }

    private static void setString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key, value);
        editor.commit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFERENCE_FILE_KEY
                , Context.MODE_PRIVATE);
    }

}

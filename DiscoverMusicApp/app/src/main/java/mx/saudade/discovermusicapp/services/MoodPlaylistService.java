package mx.saudade.discovermusicapp.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;

import org.apache.commons.lang3.BooleanUtils;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import mx.saudade.discovermusicapp.BuildConfig;
import mx.saudade.discovermusicapp.responses.Root;
import mx.saudade.discovermusicapp.utils.PreferencesUtils;

/**
 * Created by angie on 6/26/16.
 */
public class MoodPlaylistService extends TemplateService {

    public static final String TRACK_VALENCE_EXTRA = "track_valence_extra";
    public static final String TRACK_AROUSAL_EXTRA = "track_arousal_extra";
    public static final String YEAR_MAX_EXTRA = "year_max_extra";
    public static final String YEAR_MIN_EXTRA = "year_min_extra";
    public static final String BEFORE_1950_EXTRA = "before_1950_extra";
    public static final String DATE_50_EXTRA = "date_50_extra";
    public static final String DATE_60_EXTRA = "date_60_extra";
    public static final String DATE_70_EXTRA = "date_70_extra";
    public static final String DATE_80_EXTRA = "date_80_extra";
    public static final String DATE_90_EXTRA = "date_90_extra";
    public static final String DATE_00_EXTRA = "date_00_extra";
    public static final String DATE_10_EXTRA = "date_10_extra";
    public static final String GENRE_NO_EXTRA = "genre_no_extra";

    final String BASE_URL = "http://musicovery.com/api/V3/playlist.php";
    final String QUERY_FCT = "fct";
    final String FCT = "getfrommood";
    final String QUERY_RESULTS_NUMBER = "resultsnumber";
    final String QUERY_TRACK_VALENCE = "trackvalence";
    final String QUERY_TRACK_AROUSAL = "trackarousal";
    final String QUERY_YEAR_MAX = "yearmax";
    final String QUERY_YEAR_MIN = "yearmin";
    final String QUERY_BEFORE_1950 = "before1950";
    final String QUERY_DATE_50 = "date50";
    final String QUERY_DATE_60 = "date60";
    final String QUERY_DATE_70 = "date70";
    final String QUERY_DATE_80 = "date80";
    final String QUERY_DATE_90 = "date90";
    final String QUERY_DATE_00 = "date00";
    final String QUERY_DATE_10 = "date10";
    final String QUERY_POPULARITY_MAX = "popularitymax";
    final String QUERY_POPULARITY_MIN = "popularitymin";
    final String QUERY_GENRE_NO = "genreNo";
    final String QUERY_LISTENER_COUNTRY = "listenercountry";
    final String QUERY_API_KEY = "apikey";
    final String QUERY_FORMAT = "format";
    final String FORMAT = "xml";

    public MoodPlaylistService() {
        super(TAG);
    }

    @Override
    protected Uri getUri(Intent intent) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_FCT, FCT)
                .appendQueryParameter(QUERY_RESULTS_NUMBER
                        , String.valueOf(PreferencesUtils.getResultsNumber(getApplicationContext())))
                .appendQueryParameter(QUERY_TRACK_VALENCE, intent.getStringExtra(TRACK_VALENCE_EXTRA))
                .appendQueryParameter(QUERY_TRACK_AROUSAL, intent.getStringExtra(TRACK_AROUSAL_EXTRA))
                .appendQueryParameter(QUERY_YEAR_MAX, intent.getStringExtra(YEAR_MAX_EXTRA))
                .appendQueryParameter(QUERY_YEAR_MIN, intent.getStringExtra(YEAR_MIN_EXTRA))
                .appendQueryParameter(QUERY_BEFORE_1950, BooleanUtils.toStringTrueFalse(
                        intent.getBooleanExtra(BEFORE_1950_EXTRA, false)))
                .appendQueryParameter(QUERY_DATE_50, BooleanUtils.toStringTrueFalse(
                        intent.getBooleanExtra(DATE_50_EXTRA, false)))
                .appendQueryParameter(QUERY_DATE_60, BooleanUtils.toStringTrueFalse(
                        intent.getBooleanExtra(DATE_60_EXTRA, false)))
                .appendQueryParameter(QUERY_DATE_70, BooleanUtils.toStringTrueFalse(
                        intent.getBooleanExtra(DATE_70_EXTRA, false)))
                .appendQueryParameter(QUERY_DATE_80, BooleanUtils.toStringTrueFalse(
                        intent.getBooleanExtra(DATE_80_EXTRA, false)))
                .appendQueryParameter(QUERY_DATE_90, BooleanUtils.toStringTrueFalse(
                        intent.getBooleanExtra(DATE_90_EXTRA, false)))
                .appendQueryParameter(QUERY_DATE_00, BooleanUtils.toStringTrueFalse(
                        intent.getBooleanExtra(DATE_00_EXTRA, false)))
                .appendQueryParameter(QUERY_DATE_10, BooleanUtils.toStringTrueFalse(
                        intent.getBooleanExtra(DATE_10_EXTRA, false)))
                .appendQueryParameter(QUERY_POPULARITY_MAX
                        , String.valueOf(PreferencesUtils.getMaxPopularity(getApplicationContext())))
                .appendQueryParameter(QUERY_POPULARITY_MIN
                        , String.valueOf(PreferencesUtils.getMinPopularity(getApplicationContext())))
                .appendQueryParameter(QUERY_GENRE_NO, intent.getStringExtra(GENRE_NO_EXTRA))
                .appendQueryParameter(QUERY_LISTENER_COUNTRY
                        , PreferencesUtils.getLocation(getApplicationContext()))
                .appendQueryParameter(QUERY_API_KEY, BuildConfig.MUSICOVERY_API_KEY)
                .appendQueryParameter(QUERY_FORMAT, FORMAT)
                .build();
        Log.d(TAG, "URI: " + uri.toString());
        return uri;
    }

    @Override
    protected Parcelable getResult(String inputStream) throws Exception {
        Serializer serializer = new Persister();
        return serializer.read(Root.class, inputStream, false);
    }

}

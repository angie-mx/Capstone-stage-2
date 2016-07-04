package mx.saudade.discovermusicapp.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import mx.saudade.discovermusicapp.BuildConfig;
import mx.saudade.discovermusicapp.responses.Root;
import mx.saudade.discovermusicapp.utils.PreferencesUtils;

/**
 * Created by angie on 6/27/16.
 */
public class TrackPlaylistService extends TemplateService {

    public static final String TAG = TrackPlaylistService.class.getSimpleName();

    public static final String WEB_SERVICE_EVENT = TAG + "_EVENT";

    public static final String WEB_SERVICE_EXTRA = TAG + "_EXTRA";

    public static final String ID_EXTRA = "id_extra";

    private final String BASE_URL = "http://musicovery.com/api/V3/playlist.php";
    private final String QUERY_FCT = "fct";
    private final String FCT = "getfromtrack";
    private final String QUERY_ID = "id";
    private final String QUERY_RESULTS_NUMBER = "resultsnumber";
    private final String QUERY_POPULARITY_MAX = "popularitymax";
    private final String QUERY_POPULARITY_MIN = "popularitymin";
    private final String QUERY_LISTENER_COUNTRY = "listenercountry";
    private final String QUERY_API_KEY = "apikey";
    private final String QUERY_FORMAT = "format";
    private final String FORMAT = "xml";

    public TrackPlaylistService() {
        super(TAG, WEB_SERVICE_EVENT, WEB_SERVICE_EXTRA);
    }

    @Override
    protected Uri getUri(Intent intent) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_FCT, FCT)
                .appendQueryParameter(QUERY_ID, String.valueOf(intent.getIntExtra(ID_EXTRA, 0)))
                .appendQueryParameter(QUERY_RESULTS_NUMBER
                        , String.valueOf(PreferencesUtils.getResultsNumber(getApplicationContext())))
                .appendQueryParameter(QUERY_POPULARITY_MAX
                        , String.valueOf(PreferencesUtils.getMaxPopularity(getApplicationContext())))
                .appendQueryParameter(QUERY_POPULARITY_MIN
                        , String.valueOf(PreferencesUtils.getMinPopularity(getApplicationContext())))
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

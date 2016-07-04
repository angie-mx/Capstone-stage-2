package mx.saudade.discovermusicapp.services;

import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import mx.saudade.discovermusicapp.responses.LyricsResult;

/**
 * Created by angie on 6/27/16.
 */
public class LyricsService extends TemplateService {

    public static final String TAG = LyricsService.class.getSimpleName();

    public static final String WEB_SERVICE_EVENT = TAG + "_EVENT";

    public static final String WEB_SERVICE_EXTRA = TAG + "_EXTRA";

    public static final String ARTIST_EXTRA = "artist_extra";

    public static final String SONG_EXTRA = "song_extra";

    private final String BASE_URL = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect";
    private final String QUERY_ARTIST = "artist";
    private final String QUERY_SONG = "song";

    public LyricsService() {
        super(TAG, WEB_SERVICE_EVENT, WEB_SERVICE_EXTRA);
    }

    @Override
    protected Uri getUri(Intent intent) {
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_ARTIST, intent.getStringExtra(ARTIST_EXTRA))
                .appendQueryParameter(QUERY_SONG, intent.getStringExtra(SONG_EXTRA))
                .build();
        Log.d(TAG, "URI: " + uri.toString());
        return uri;
    }

    @Override
    protected Parcelable getResult(String inputStream) throws Exception {
        Serializer serializer = new Persister();
        return serializer.read(LyricsResult.class, inputStream, false);
    }
}

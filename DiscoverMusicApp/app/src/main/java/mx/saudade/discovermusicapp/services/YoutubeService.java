package mx.saudade.discovermusicapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.List;

import mx.saudade.discovermusicapp.BuildConfig;

/**
 * Created by angie on 7/4/16.
 */
public class YoutubeService extends IntentService {

    private static final String TAG = YoutubeService.class.getSimpleName();

    public static final String WEB_SERVICE_EVENT = TAG + "_EVENT";

    public static final String WEB_SERVICE_EXTRA = TAG + "_EXTRA";

    public static final String SEARCH_ID_EXTRA = TAG + "_search_id_extra";

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;

    private static YouTube youtube;

    public YoutubeService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException { }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            String queryTerm = intent.getStringExtra(SEARCH_ID_EXTRA);

            YouTube.Search.List search = youtube.search().list("id,snippet");

            String apiKey = BuildConfig.GOOGLE_API_KEY;
            search.setKey(apiKey);
            search.setQ(queryTerm);

            search.setType("video");

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null && searchResultList.size() > 0) {
                String id = searchResultList.get(0).getId().getVideoId();
                sendMessage(id);
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    private void sendMessage(String id) {
        Log.d(TAG, "Broadcasting result: " + id);
        Intent intent = new Intent(WEB_SERVICE_EVENT);
        intent.putExtra(WEB_SERVICE_EXTRA, id);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}

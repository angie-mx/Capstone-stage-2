package mx.saudade.discovermusicapp.activities;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.saudade.discovermusicapp.AnalyticsApplication;
import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.data.AppContract;
import mx.saudade.discovermusicapp.data.AppCursorHelper;
import mx.saudade.discovermusicapp.responses.Track;
import mx.saudade.discovermusicapp.utils.PlaylistUtils;

/**
 * Created by angie on 7/3/16.
 */
public class FavoritesActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = FavoritesActivity.class.getSimpleName();

    private static final int URL_LOADER = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.playlist_fragment);
        getSupportLoaderManager().initLoader(URL_LOADER, null, this);
        ((AnalyticsApplication) getApplication()).trackScreen(getString(R.string.favorites));
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                this,
                AppContract.TrackEntry.CONTENT_URI,
                AppContract.TrackEntry.COMPLETE_PROJECTION,
                null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        AppCursorHelper cursorHelper = new AppCursorHelper(this);

        List<Track> tracks = new ArrayList<Track>();

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                Track track = new Track();
                track.setId(cursor.getInt(AppContract.TrackEntry.INDEX_COLUMN_ID));
                track.setTitle(cursor.getString(AppContract.TrackEntry.INDEX_COLUMN_TITLE));
                track.setArtist(cursorHelper.getArtist(cursor.getInt(AppContract.TrackEntry
                        .INDEX_COLUMN_ARTIST_ID)));
                track.setReleaseDate(cursor.getString(AppContract.TrackEntry
                        .INDEX_COLUMN_RELEASE_DATE));
                track.setGenre(cursor.getString(AppContract.TrackEntry.INDEX_COLUMN_GENRE));
                track.setArousal(cursor.getInt(AppContract.TrackEntry.INDEX_COLUMN_AROUSAL));
                track.setValence(cursor.getInt(AppContract.TrackEntry.INDEX_COLUMN_VALENCE));
                track.setLyrics(cursor.getString(AppContract.TrackEntry.INDEX_COLUMN_LYRICS));
                tracks.add(track);
            } while (cursor.moveToNext());
        }

        Log.d(TAG, "tracks " + tracks);
        updateContent(tracks);
    }

    private void updateContent(List<Track> tracks) {
        if (tracks.size() == 0) {
            getTextView().setText(R.string.no_favorites);
            getTextView().setVisibility(TextView.VISIBLE);
        } else {
            PlaylistUtils.createRecyclerView(this, findViewById(R.id.playlist_container), tracks);
            getTextView().setVisibility(TextView.GONE);
        }
    }

    private TextView getTextView() {
        return (TextView) findViewById(R.id.playlist_no_results);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

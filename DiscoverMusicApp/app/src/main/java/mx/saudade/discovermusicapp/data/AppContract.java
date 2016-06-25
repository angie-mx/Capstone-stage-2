package mx.saudade.discovermusicapp.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import mx.saudade.discovermusicapp.BuildConfig;

/**
 * Created by angie on 6/24/16.
 */
public class AppContract {

    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ARTIST = "artist";

    public static final String PATH_TRACK = "track";

    public static final class ArtistEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTIST).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ARTIST;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ARTIST;

        public static final String TABLE_NAME = "artist";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_IMG_URL = "img_url";

        public static final int INDEX_COLUMN_ID = 0;

        public static final int INDEX_COLUMN_NAME = 1;

        public static final int INDEX_COLUMN_IMG_URL = 2;

        public static final String[] COMPLETE_PROJECTION = {
                ArtistEntry._ID,
                ArtistEntry.COLUMN_NAME,
                ArtistEntry.COLUMN_IMG_URL
        };

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class TrackEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_TRACK).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRACK;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TRACK;

        public static final String TABLE_NAME = "track";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_ARTIST_ID = "artist_id";

        public static final String COLUMN_RELEASE_DATE = "release_date";

        public static final String COLUMN_GENRE = "genre";

        public static final String COLUMN_AROUSAL = "arousal";

        public static final String COLUMN_VALENCE = "valence";

        public static final String COLUMN_LYRICS = "lyrics";

        public static final int INDEX_COLUMN_ID = 0;

        public static final int INDEX_COLUMN_TITLE = 1;

        public static final int INDEX_COLUMN_ARTIST_ID = 2;

        public static final int INDEX_COLUMN_RELEASE_DATE = 3;

        public static final int INDEX_COLUMN_GENRE = 4;

        public static final int INDEX_COLUMN_AROUSAL = 5;

        public static final int INDEX_COLUMN_VALENCE = 6;

        public static final int INDEX_COLUMN_LYRICS = 7;

        public static final String[] COMPLETE_PROJECTION = {
                TrackEntry._ID,
                TrackEntry.COLUMN_TITLE,
                TrackEntry.COLUMN_ARTIST_ID,
                TrackEntry.COLUMN_RELEASE_DATE,
                TrackEntry.COLUMN_GENRE,
                TrackEntry.COLUMN_AROUSAL,
                TrackEntry.COLUMN_VALENCE,
                TrackEntry.COLUMN_LYRICS
        };

        public static Uri buildLocationUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}

package mx.saudade.discovermusicapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mx.saudade.discovermusicapp.data.AppContract.ArtistEntry;
import mx.saudade.discovermusicapp.data.AppContract.TrackEntry;

/**
 * Created by angie on 6/23/16.
 */
public class AppDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "music.db";

    public AppDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ARTIST_TABLE =
                "CREATE TABLE " + ArtistEntry.TABLE_NAME + " (" +
                        ArtistEntry._ID + " INTEGER PRIMARY KEY, " +
                        ArtistEntry.COLUMN_NAME + " VARCHAR(100), " +
                        ArtistEntry.COLUMN_IMG_URL + " VARCHAR(255) " +
                        ");";

        final String SQL_CREATE_TRACK_TABLE =
                "CREATE TABLE " + TrackEntry.TABLE_NAME + " (" +
                        TrackEntry._ID + " INTEGER PRIMARY KEY, " +
                        TrackEntry.COLUMN_TITLE + " VARCHAR(100), " +
                        TrackEntry.COLUMN_ARTIST_ID + " INTEGER, " +
                        TrackEntry.COLUMN_RELEASE_DATE + " VARCHAR(100), " +
                        TrackEntry.COLUMN_GENRE + " VARCHAR(100), " +
                        TrackEntry.COLUMN_AROUSAL + " INTEGER, " +
                        TrackEntry.COLUMN_VALENCE + " INTEGER, " +
                        TrackEntry.COLUMN_LYRICS + " TEXT" +
                        ");";

        db.execSQL(SQL_CREATE_ARTIST_TABLE);
        db.execSQL(SQL_CREATE_TRACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        final String SQL_DROP_MOVIE_ARTIST = "DROP TABLE IF EXISTS " + ArtistEntry.TABLE_NAME;
        final String SQL_DROP_REVIEW_TRACK = "DROP TABLE IF EXISTS " + TrackEntry.TABLE_NAME;

        db.execSQL(SQL_DROP_MOVIE_ARTIST);
        db.execSQL(SQL_DROP_REVIEW_TRACK);

        this.onCreate(db);
    }

}

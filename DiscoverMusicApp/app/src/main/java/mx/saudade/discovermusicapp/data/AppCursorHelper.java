package mx.saudade.discovermusicapp.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mx.saudade.discovermusicapp.responses.Artist;
import mx.saudade.discovermusicapp.responses.Track;

import mx.saudade.discovermusicapp.data.AppContract.*;

/**
 * Created by angie on 6/26/16.
 */

public class AppCursorHelper {

    private static final String TAG = AppCursorHelper.class.getSimpleName();

    private Context context;

    public AppCursorHelper(Context context) {
        this.context = context;
    }

    public Artist getArtist(int id) {
        if (id <= 0) {
            return null;
        }

        Artist artist = null;

        Cursor cursor = context.getContentResolver().query(
                ArtistEntry.CONTENT_URI,
                ArtistEntry.COMPLETE_PROJECTION,
                ArtistEntry._ID + " = ?",
                new String[] {String.valueOf(id)},
                null);

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            artist = new Artist();
            artist.setId(cursor.getInt(ArtistEntry.INDEX_COLUMN_ID));
            artist.setName(cursor.getString(ArtistEntry.INDEX_COLUMN_NAME));
            artist.setImgUrl(cursor.getString(ArtistEntry.INDEX_COLUMN_IMG_URL));
        }
        cursor.close();

        Log.d(TAG, "get Artist: " + artist);

        return artist;
    }

    public Track getTrack(int id) {
        if (id <= 0) {
            return null;
        }

        Track track = null;

        Cursor cursor = context.getContentResolver().query(
                TrackEntry.CONTENT_URI,
                TrackEntry.COMPLETE_PROJECTION,
                TrackEntry._ID + "= ?",
                new String[] {String.valueOf(id)},
                null);

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            track = new Track();
            track.setId(cursor.getInt(TrackEntry.INDEX_COLUMN_ID));
            track.setTitle(cursor.getString(TrackEntry.INDEX_COLUMN_TITLE));
            track.setArtist(getArtist(cursor.getInt(TrackEntry.INDEX_COLUMN_ARTIST_ID)));
            track.setReleaseDate(cursor.getInt(TrackEntry.INDEX_COLUMN_RELEASE_DATE));
            track.setGenre(cursor.getString(TrackEntry.INDEX_COLUMN_GENRE));
            track.setArousal(cursor.getInt(TrackEntry.INDEX_COLUMN_AROUSAL));
            track.setValence(cursor.getInt(TrackEntry.INDEX_COLUMN_VALENCE));
            track.setLyrics(cursor.getString(TrackEntry.INDEX_COLUMN_LYRICS));
        }
        cursor.close();

        Log.d(TAG, "get Track: " + track);

        return track;
    }

    public List<Track> getTracks() {
        List<Track> tracks = new ArrayList<Track>();

        Cursor cursor = context.getContentResolver().query(
                TrackEntry.CONTENT_URI,
                TrackEntry.COMPLETE_PROJECTION,
                null, null, null);

        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
            do {
                Track track = new Track();
                track.setId(cursor.getInt(TrackEntry.INDEX_COLUMN_ID));
                track.setTitle(cursor.getString(TrackEntry.INDEX_COLUMN_TITLE));
                track.setArtist(getArtist(cursor.getInt(TrackEntry.INDEX_COLUMN_ARTIST_ID)));
                track.setReleaseDate(cursor.getInt(TrackEntry.INDEX_COLUMN_RELEASE_DATE));
                track.setGenre(cursor.getString(TrackEntry.INDEX_COLUMN_GENRE));
                track.setArousal(cursor.getInt(TrackEntry.INDEX_COLUMN_AROUSAL));
                track.setValence(cursor.getInt(TrackEntry.INDEX_COLUMN_VALENCE));
                track.setLyrics(cursor.getString(TrackEntry.INDEX_COLUMN_LYRICS));
                tracks.add(track);
            } while (cursor.moveToNext());
        }
        cursor.close();

        Log.d(TAG, "get Tracks: " + tracks);

        return tracks;
    }

    public long insertArtist(Artist artist) {
        if (artist == null || artist.getId() <= 0) {
            return 0;
        }

        Artist a = getArtist(artist.getId());
        if (a != null) {
            return 0;
        }

        ContentValues values = new ContentValues();
        values.put(ArtistEntry._ID, artist.getId());
        values.put(ArtistEntry.COLUMN_NAME, artist.getName());
        values.put(ArtistEntry.COLUMN_IMG_URL, artist.getImgUrl());

        Uri insertedUri = context.getContentResolver().insert(ArtistEntry.CONTENT_URI, values);

        Log.d(TAG, "inserted Artist: " + insertedUri);

        return ContentUris.parseId(insertedUri);
    }

    public long insertTrack(Track track) {
        if (track == null || track.getId() <= 0) {
            return 0;
        }

        Track t = getTrack(track.getId());
        if (t != null) {
            return 0;
        }

        insertArtist(track.getArtist());

        ContentValues values = new ContentValues();
        values.put(TrackEntry._ID, track.getId());
        values.put(TrackEntry.COLUMN_TITLE, track.getTitle());
        values.put(TrackEntry.COLUMN_ARTIST_ID, track.getArtist().getId());
        values.put(TrackEntry.COLUMN_RELEASE_DATE, track.getReleaseDate());
        values.put(TrackEntry.COLUMN_GENRE, track.getGenre());
        values.put(TrackEntry.COLUMN_AROUSAL, track.getArousal());
        values.put(TrackEntry.COLUMN_VALENCE, track.getValence());
        values.put(TrackEntry.COLUMN_LYRICS, track.getLyrics());

        Uri insertedUri = context.getContentResolver().insert(TrackEntry.CONTENT_URI, values);

        Log.d(TAG, "inserted Track: " + insertedUri);

        return ContentUris.parseId(insertedUri);
    }

    public long deleteArtist(int id) {
        if (id <= 0) {
            return 0;
        }

        Log.d(TAG, "delete Artist: " + id);

        return context.getContentResolver().delete(ArtistEntry.CONTENT_URI, ArtistEntry._ID + " = ?"
        , new String[] {String.valueOf(id)});
    }

    public long deleteTrack(int id) {
        if (id <= 0) {
            return 0;
        }

        Log.d(TAG, "delete Track: " + id);

        return context.getContentResolver().delete(TrackEntry.CONTENT_URI, TrackEntry._ID + " = ?"
                , new String[] {String.valueOf(id)});
    }
}

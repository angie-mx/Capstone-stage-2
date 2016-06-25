package mx.saudade.discovermusicapp.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by angie on 6/24/16.
 */
public class AppContentProvider extends ContentProvider {

    private static final int ARTIST = 101;
    private static final int TRACK = 102;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    private AppDataBaseHelper dataBaseHelper;

    @Override
    public boolean onCreate() {
        dataBaseHelper = new AppDataBaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs
            , String sortOrder) {
        Cursor result;
        final int match = uriMatcher.match(uri);
        switch (match) {
            case ARTIST: {
                result = dataBaseHelper.getReadableDatabase().query(AppContract.ArtistEntry
                        .TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            case TRACK: {
                result = dataBaseHelper.getReadableDatabase().query(AppContract.TrackEntry
                        .TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }
        result.setNotificationUri(getContext().getContentResolver(), uri);
        return result;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        int match = uriMatcher.match(uri);
        switch (match) {
            case ARTIST:
                return AppContract.ArtistEntry.CONTENT_TYPE;
            case TRACK:
                return AppContract.TrackEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        Uri result;
        switch (match) {
            case ARTIST: {
                long id = db.insert(AppContract.ArtistEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    result = AppContract.ArtistEntry.buildLocationUri(id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case TRACK: {
                long id = db.insert(AppContract.TrackEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    result = AppContract.TrackEntry.buildLocationUri(id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsDeleted;
        if (selection == null) selection = "1";
        switch (match) {
            case ARTIST:
                rowsDeleted = db.delete(AppContract.ArtistEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case TRACK:
                rowsDeleted = db.delete(AppContract.TrackEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int rowsUpdated = 0;
        switch (match) {
            case ARTIST:
                rowsUpdated = db.update(AppContract.ArtistEntry.TABLE_NAME, values, selection
                        , selectionArgs);
                break;
            case TRACK:
                rowsUpdated = db.update(AppContract.TrackEntry.TABLE_NAME, values, selection
                        , selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        final int match = uriMatcher.match(uri);

        switch (match) {
            case ARTIST:
                return setTransaction(db, AppContract.ArtistEntry.TABLE_NAME, uri, values);
            case TRACK:
                return setTransaction(db, AppContract.TrackEntry.TABLE_NAME, uri, values);
            default:
                return super.bulkInsert(uri, values);
        }
    }

    private int setTransaction(SQLiteDatabase db, String tableName, Uri uri, ContentValues[] values) {
        db.beginTransaction();
        int count = 0;
        try {
            for (ContentValues value : values) {
                long id = db.insert(tableName, null, value);
                if (id != -1) {
                    count++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = AppContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, AppContract.PATH_ARTIST, ARTIST);
        matcher.addURI(authority, AppContract.PATH_TRACK, TRACK);

        return matcher;
    }

}

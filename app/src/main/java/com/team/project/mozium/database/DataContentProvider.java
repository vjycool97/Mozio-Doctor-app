package com.team.project.mozium.database;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class DataContentProvider extends ContentProvider {

    private SQLiteOpenHelper sqLiteOpenHelper;

    private static final int TEXT_PATIENTS = 10;

    private static final String BASE_PATH_PATIENTS = "patients";

    private static final String AUTHORITY = "com.team.project.contentprovider";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH_PATIENTS);

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH_PATIENTS, TEXT_PATIENTS);
    }

    @Override
    public boolean onCreate() {
        sqLiteOpenHelper = new DataDatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        int uriType = sURIMatcher.match(uri);
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        SQLiteDatabase sqlDB = sqLiteOpenHelper.getWritableDatabase();
        long id;
        switch (uriType) {
            case TEXT_PATIENTS:
                queryBuilder.setTables(PatientDataTable.TABLE_DATA_TEXT);
                Cursor cursor = queryBuilder.query(sqlDB, projection, selection,
                        selectionArgs, null, null, sortOrder, null);
                // make sure that potential listeners are getting notified
                cursor.setNotificationUri(getContext().getContentResolver(), uri);
                return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = sqLiteOpenHelper.getWritableDatabase();
        long id;
        try {
            switch (uriType) {
                case TEXT_PATIENTS:
                    id = sqlDB.insertWithOnConflict(PatientDataTable.TABLE_DATA_TEXT, null, values, SQLiteDatabase.CONFLICT_IGNORE);
                    getContext().getContentResolver().notifyChange(uri, null);
                    break;
            }
        }catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
        return uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

package com.team.project.mozium.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;


public class DataDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "patients.db";
    private static final int DATABASE_VERSION = 2;

    public DataDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(@NonNull SQLiteDatabase database) {
        try {
            PatientDataTable.onCreate(database);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(@NonNull SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        try {
            PatientDataTable.onUpgrade(database, oldVersion, newVersion);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }
    }
}

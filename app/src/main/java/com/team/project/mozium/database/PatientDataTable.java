package com.team.project.mozium.database;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

public class PatientDataTable {

    /**
     * The constant TABLE_DATA_TEXT.
     */
    public static final String TABLE_DATA_TEXT = "patients";
    /**
     * The constant COLUMN_ID.
     */
    public static final String COLUMN_ID = "_id";
    /**
     * The constant PATIENT_NAME.
     */
    public static final String PATIENT_NAME = "patient_name";
    /**
     * The constant GENDER.
     */
    public static final String GENDER = "gender";
    /**
     * The constant MIGRAINE.
     */
    public static final String MIGRAINE = "migraine";
    /**
     * The constant DRUGS.
     */
    public static final String DRUGS = "drugs";
    /**
     * The constant AGE.
     */
    public static final String AGE = "age";

    /**
     * The constant ALL_COLUMNS.
     */
    public static final String[] ALL_COLUMNS = {
            COLUMN_ID, PATIENT_NAME, GENDER, MIGRAINE, DRUGS, AGE
    };
    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_DATA_TEXT
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + PATIENT_NAME + " text not null , "
            + GENDER + " integer default 0, "
            + MIGRAINE + " integer default false, "
            + DRUGS + " integer default false,"
            + AGE + " integer );";

    /**
     * On create.
     *
     * @param database the database
     */
    public static void onCreate(@NonNull SQLiteDatabase database) {
        try {
            database.execSQL(DATABASE_CREATE);
        } catch (Exception e) {
            Log.e(PatientDataTable.class.getSimpleName(), e.getMessage());
        }
    }

    /**
     * On upgrade.
     *
     * @param database   the database
     * @param oldVersion the old version
     * @param newVersion the new version
     * @throws Exception the exception
     */
    static void onUpgrade(@NonNull SQLiteDatabase database, int oldVersion,
                          int newVersion) throws Exception {
        Log.w(PatientDataTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA_TEXT);
        onCreate(database);
    }

}

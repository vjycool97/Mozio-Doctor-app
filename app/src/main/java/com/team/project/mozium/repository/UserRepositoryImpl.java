package com.team.project.mozium.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.team.project.mozium.database.DataContentProvider;
import com.team.project.mozium.database.PatientDataTable;
import com.team.project.mozium.models.Patients;

import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<Patients> getPatients(Context context) {

        String[] projection = {
                PatientDataTable.PATIENT_NAME,
                PatientDataTable.AGE,
                PatientDataTable.DRUGS,
                PatientDataTable.GENDER,
                PatientDataTable.MIGRAINE
        };
        Cursor cursor = context.getContentResolver().query(DataContentProvider.CONTENT_URI, projection, null, null, null);
        return mapCursor(cursor);
    }

    @Override
    public void submitPatient(Context context, String name, String age, int sexState, int drugsState, int migraineState) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(PatientDataTable.AGE, Integer.parseInt(age));
        contentValues.put(PatientDataTable.DRUGS, drugsState);
        contentValues.put(PatientDataTable.GENDER, sexState);
        contentValues.put(PatientDataTable.MIGRAINE, migraineState);
        contentValues.put(PatientDataTable.PATIENT_NAME, name);
        context.getContentResolver().insert(DataContentProvider.CONTENT_URI, contentValues);
    }

    private List<Patients> mapCursor(Cursor cursor) {
        List<Patients> patientsList = new ArrayList<>();
        while (cursor.moveToNext()) {
            patientsList.add(
                    new Patients(
                            cursor.getString(cursor.getColumnIndex(PatientDataTable.PATIENT_NAME)),
                            cursor.getInt(cursor.getColumnIndex(PatientDataTable.AGE)),
                            cursor.getInt(cursor.getColumnIndex(PatientDataTable.DRUGS)),
                            cursor.getInt(cursor.getColumnIndex(PatientDataTable.GENDER)),
                            cursor.getInt(cursor.getColumnIndex(PatientDataTable.MIGRAINE))
                    )
            );
        }
        return patientsList;
    }
}

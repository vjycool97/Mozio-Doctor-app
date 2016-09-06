package com.team.project.mozium.repository;

import android.content.Context;

import com.team.project.mozium.models.Patients;

import java.util.List;

public interface UserRepository {

    List<Patients> getPatients(Context context);

    void submitPatient(Context context, String name, String age, int sexState, int drugsState, int migraineState);
}

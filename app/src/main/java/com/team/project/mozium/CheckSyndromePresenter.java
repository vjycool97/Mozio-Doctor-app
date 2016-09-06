package com.team.project.mozium;

import android.content.Context;

import com.team.project.mozium.models.Patients;
import com.team.project.mozium.models.ResolvedPatients;
import com.team.project.mozium.repository.UserRepository;
import com.team.project.mozium.utils.KeyIds;

import java.util.ArrayList;
import java.util.List;


public class CheckSyndromePresenter {

    private UserRepository userRepository;
    private CheckSyndromeInterface checkSyndromeInterface;

    public CheckSyndromePresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getPatients(Context context) {
        checkSyndromeInterface.swapAdapter(mapPatients(userRepository.getPatients(context)));

    }

    private List<ResolvedPatients> mapPatients(List<Patients> patientsList) {
        List<ResolvedPatients> resolvedPatientsList = new ArrayList<>();
        for (Patients patient : patientsList) {
            String message;
            int symptomsCount = checkConditions(patient);
            switch (symptomsCount) {
                case 1: message = "You have 25% chance of having Todd's syndrome";
                    break;
                case 2: message = "You have 50% chance of having Todd's syndrome";
                    break;
                case 3: message = "You have 75% chance of having Todd's syndrome";
                    break;
                case 4: message = "You have 100% chance of having Todd's syndrome";
                    break;
                default: message = "Congrats. You have no chance of having Todd's syndrome";
                    break;
            }

            resolvedPatientsList.add(new ResolvedPatients(patient.getName(), message, symptomsCount));
        }
        return resolvedPatientsList;
    }

    private int checkConditions(Patients patient) {
        int symptoms = 0;
        if (patient.getSex() == KeyIds.IS_MALE) {
            symptoms++;
        }
        if (patient.getAge() > 15) {
            symptoms++;
        }
        if (patient.getTakesDrugs() == KeyIds.TAKES_DRUGS_YES) {
            symptoms++;
        }
        if (patient.getHasMigraine() == KeyIds.HAS_MIGRAINE_YES) {
            symptoms++;
        }
        return symptoms;
    }

    public void setView(CheckSyndromeInterface checkSyndromeInterface) {
        this.checkSyndromeInterface = checkSyndromeInterface;
    }

    public void onDestroy() {
        checkSyndromeInterface = null;
    }
}

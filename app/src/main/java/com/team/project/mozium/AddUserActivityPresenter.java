package com.team.project.mozium;

import android.content.Context;

import com.team.project.mozium.repository.UserRepository;


public class AddUserActivityPresenter {

    private AddUserInterface addUserInterface;

    private UserRepository userRepository;

    public AddUserActivityPresenter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void onSubmitClicked(Context context, String name, String age, int sexState, int drugsState, int migraineState){
        userRepository.submitPatient(
                context,
                name,
                age,
                sexState,
                drugsState,
                migraineState
        );

        addUserInterface.userSaved();
    }

    public void setView(AddUserInterface addUserInterface) {
        this.addUserInterface = addUserInterface;
    }

    void onDestroy(){
        addUserInterface = null;
    }

    public void onVisitClicked() {
        addUserInterface.visitPatientRecords();
    }
}

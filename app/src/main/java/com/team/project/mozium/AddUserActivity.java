package com.team.project.mozium;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.team.project.mozium.repository.UserRepositoryImpl;
import com.team.project.mozium.utils.KeyIds;

public class AddUserActivity extends AppCompatActivity  implements AddUserInterface{

    private EditText name;
    private Switch toggleSex;
    private Switch toggleDrugs;
    private Switch toggleMigraine;
    private EditText age;
    private TextView submitButton;
    private TextView buttonVisit;
    private AddUserActivityPresenter addUserActivityPresenter;
    private int sexState;
    private int drugsState;
    private int migraineState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        init();

        toggleMigraine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    migraineState = KeyIds.HAS_MIGRAINE_YES;
                }
                else {
                    migraineState = KeyIds.HAS_MIGRAINE_NO;
                }
            }
        });

        toggleSex.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sexState = KeyIds.IS_MALE;
                }
                else {
                    sexState = KeyIds.IS_FEMALE;
                }
            }
        });

        toggleDrugs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    drugsState = KeyIds.TAKES_DRUGS_YES;
                }
                else {
                    drugsState = KeyIds.TAKES_DRUGS_NO;
                }
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    addUserActivityPresenter.onSubmitClicked(
                            AddUserActivity.this,
                            name.getText().toString().trim(),
                            age.getText().toString().trim(),
                            sexState,
                            drugsState,
                            migraineState
                    );
                }
            }
        });

        buttonVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserActivityPresenter.onVisitClicked();
            }
        });

    }

    private boolean validate() {
        boolean result = true;

        if (TextUtils.isEmpty(name.getText())){
            name.setError("Please fill in a name");
            result = false;
        }

        if (TextUtils.isEmpty(age.getText())) {
            age.setError("Please fill in an age");
            result = false;
        }

        return result;
    }

    private void init() {
        addUserActivityPresenter = new AddUserActivityPresenter(new UserRepositoryImpl());
        addUserActivityPresenter.setView(this);

        name = (EditText)findViewById(R.id.text_name);
        age = (EditText)findViewById(R.id.text_age);
        toggleDrugs = (Switch) findViewById(R.id.toggle_drugs);
        toggleSex = (Switch)findViewById(R.id.toggle_sex);
        toggleMigraine = (Switch)findViewById(R.id.toggle_migraine);
        submitButton = (TextView)findViewById(R.id.button_submit);
        buttonVisit = (TextView)findViewById(R.id.button_visit);
        sexState = KeyIds.IS_FEMALE;
        drugsState = KeyIds.TAKES_DRUGS_NO;
        migraineState = KeyIds.HAS_MIGRAINE_NO;
    }

    @Override
    public void userSaved() {
        Toast.makeText(this, "Patient saved successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        addUserActivityPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.support_slide_from_right);
    }

    @Override
    public void visitPatientRecords(){
        startActivity(new Intent(this, CheckSyndromeActivity.class));
        overridePendingTransition(R.anim.support_slide_from_left, R.anim.support_slide_from_right);
    }
}

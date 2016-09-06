package com.team.project.mozium;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.team.project.mozium.models.ResolvedPatients;
import com.team.project.mozium.repository.UserRepositoryImpl;

import java.util.List;

public class CheckSyndromeActivity extends AppCompatActivity implements CheckSyndromeInterface {

    private CheckSyndromePresenter checkSyndromePresenter;
    private RecyclerView recyclerView;
    private PatientAdapter patientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_syndrome);
        init();

        checkSyndromePresenter.getPatients(this);
    }

    private void init() {
        checkSyndromePresenter = new CheckSyndromePresenter(new UserRepositoryImpl());
        checkSyndromePresenter.setView(this);
        patientAdapter = new PatientAdapter(null, this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_patients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(patientAdapter);
    }

    @Override
    public void swapAdapter(List<ResolvedPatients> resolvedPatientsList) {
        patientAdapter.swapAdapter(resolvedPatientsList);
    }

    @Override
    protected void onDestroy() {
        checkSyndromePresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.nothing, R.anim.support_slide_from_right);
    }
}

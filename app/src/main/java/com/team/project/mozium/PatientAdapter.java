package com.team.project.mozium;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.project.mozium.models.ResolvedPatients;

import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    private List<ResolvedPatients> patientsList;
    private Context context;

    public PatientAdapter(List<ResolvedPatients> patientsList, Context context) {
        this.patientsList = patientsList;
        this.context = context;
    }

    @Override
    public PatientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatientViewHolder holder, int position) {
        ResolvedPatients resolvedPatients = patientsList.get(position);
        holder.message.setText(resolvedPatients.getMessage());
        holder.patientName.setText(resolvedPatients.getName());
    }

    @Override
    public int getItemCount() {
        return patientsList.size();
    }

    void swapAdapter(List<ResolvedPatients> resolvedPatientsList) {
        this.patientsList = resolvedPatientsList;
        notifyDataSetChanged();
    }

    static class PatientViewHolder extends RecyclerView.ViewHolder {

        TextView patientName;
        TextView message;

        PatientViewHolder(View itemView) {
            super(itemView);
            patientName = (TextView)itemView.findViewById(R.id.text_name);
            message = (TextView)itemView.findViewById(R.id.text_message);
        }
    }
}

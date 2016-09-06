package com.team.project.mozium.models;


public class ResolvedPatients {

    private String name;
    private String message;
    private int numberOfSymptoms;

    public ResolvedPatients(String name, String message, int numberOfSymptoms) {
        this.name = name;
        this.message = message;
        this.numberOfSymptoms = numberOfSymptoms;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public int getNumberOfSymptoms() {
        return numberOfSymptoms;
    }
}

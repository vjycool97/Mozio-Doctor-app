package com.team.project.mozium.models;


public class Patients {

    private String name;
    private int age;
    private int takesDrugs;
    private int sex;
    private int hasMigraine;

    public Patients(String name, int age, int takesDrugs, int sex, int hasMigraine) {
        this.name = name;
        this.age = age;
        this.takesDrugs = takesDrugs;
        this.sex = sex;
        this.hasMigraine = hasMigraine;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getTakesDrugs() {
        return takesDrugs;
    }

    public int getSex() {
        return sex;
    }

    public int getHasMigraine() {
        return hasMigraine;
    }
}

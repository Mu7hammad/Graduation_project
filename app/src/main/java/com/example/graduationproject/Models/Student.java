package com.example.graduationproject.Models;

public class Student {
    private String name;
    private String code;
    private float gpa;
    private String specialization;
    private String level;
    private String classification;
    private int fees;

    public Student(String name, String code, float gpa, String specialization, String level, String classification, int fees) {
        this.name = name;
        this.code = code;
        this.gpa = gpa;
        this.specialization = specialization;
        this.level = level;
        this.classification = classification;
        this.fees = fees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }
}

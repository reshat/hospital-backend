package com.sbkm.hospital;

public class PatientList {
    private String name;
    private String surname;
    private String patronymic;
    private String dateOfReceipt;
    private String timeOfReceipt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(String dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public String getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public void setTimeOfReceipt(String timeOfReceipt) {
        this.timeOfReceipt = timeOfReceipt;
    }

    public PatientList() {
    }

    public PatientList(String name, String surname, String patronymic, String dateOfReceipt, String timeOfReceipt) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfReceipt = dateOfReceipt;
        this.timeOfReceipt = timeOfReceipt;
    }
}

package com.sbkm.hospital;

import java.time.LocalDate;

public class ViewRecords {
    private String name;
    private String surname;
    private String patronymic;
    private String dateOfReceipt;
    private String record;

    public ViewRecords() {
    }

    public ViewRecords(String name, String surname, String patronymic, String dateOfReceipt, String record) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.dateOfReceipt = dateOfReceipt;
        this.record = record;
    }

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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}

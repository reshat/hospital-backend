package com.sbkm.hospital;

public class AppointmentInfo {
    private String name;
    private String surname;
    private String patronymic;
    private String date_of_reciept;
    private String time_of_reciept;

    public AppointmentInfo(String name, String surname, String patronymic, String date_of_reciept, String time_of_reciept) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.date_of_reciept = date_of_reciept;
        this.time_of_reciept = time_of_reciept;
    }

    public AppointmentInfo() {
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

    public String getDate_of_reciept() {
        return date_of_reciept;
    }

    public void setDate_of_reciept(String date_of_reciept) {
        this.date_of_reciept = date_of_reciept;
    }

    public String getTime_of_reciept() {
        return time_of_reciept;
    }

    public void setTime_of_reciept(String time_of_reciept) {
        this.time_of_reciept = time_of_reciept;
    }
}

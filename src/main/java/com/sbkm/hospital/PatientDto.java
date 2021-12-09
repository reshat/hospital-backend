package com.sbkm.hospital;


import java.time.LocalDate;

public class PatientDto {
    private Long patient_id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;

    public PatientDto() {
    }

    public PatientDto(Long patient_id, String name, String surname, String patronymic, LocalDate birthDate) {
        this.patient_id = patient_id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}

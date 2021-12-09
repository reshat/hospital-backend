package com.sbkm.hospital;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Doctor")
@JsonIgnoreProperties({ "timetables", "appointmentTables", "patientRecords" })
public class Doctor {
    @Id
    private Long id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
            name = "surname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String surname;
    @Column(
            name = "patronymic",
            columnDefinition = "TEXT"
    )
    private String patronymic;
    @Column(
            name = "specialization",
            columnDefinition = "TEXT"
    )
    private String specialization;
    @Column(
            name = "work_experiences",
            columnDefinition = "TEXT"
    )
    private String work_experiences;

    @OneToMany(mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    //@JsonManagedReference
    private List<Timetable> timetables;
    @OneToMany(mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    //@JsonManagedReference
    private List<PatientRecord> patientRecords;

    public Doctor(User user, String specialization, String work_experiences) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.patronymic = user.getPatronymic();
        this.specialization = specialization;
        this.work_experiences = work_experiences;
    }

    public Doctor() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setTimetables(List<Timetable> timetable) {
        this.timetables = timetable;
    }

    public List<Timetable> getTimetables() {
        return timetables;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getWork_experiences() {
        return work_experiences;
    }

    public void setWork_experiences(String work_experiences) {
        this.work_experiences = work_experiences;
    }

    public List<PatientRecord> getPatientRecords() {
        return patientRecords;
    }

    public void setPatientRecords(List<PatientRecord> patientRecords) {
        this.patientRecords = patientRecords;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", specialization='" + specialization + '\'' +
                ", work_experiences='" + work_experiences + '\'' +
                '}';
    }
}
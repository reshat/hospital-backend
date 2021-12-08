package com.sbkm.hospital;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name="Patient")
@JsonIgnoreProperties({ "timetable", "patientRecords" })
public class Patient {
    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
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
            name="birth_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate birthDate;

    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    //@JsonManagedReference
    private List<AppointmentTable> appointmentTables;
    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    //@JsonManagedReference
    private List<PatientRecord> patientRecords;

    public Patient(String name, String surname, String patronymic, LocalDate birth_date) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birth_date;
    }

    public Patient() {
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birth_date) {
        this.birthDate = birth_date;
    }

    public List<AppointmentTable> getAppointmentTables() {
        return appointmentTables;
    }

    public void setAppointmentTables(List<AppointmentTable> appointmentTables) {
        this.appointmentTables = appointmentTables;
    }

    public List<PatientRecord> getPatientRecords() {
        return patientRecords;
    }

    public void setPatientRecords(List<PatientRecord> patientRecords) {
        this.patientRecords = patientRecords;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birth_date=" + birthDate +
                '}';
    }
}

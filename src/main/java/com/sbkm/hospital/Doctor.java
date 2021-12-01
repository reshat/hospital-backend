package com.sbkm.hospital;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Doctor")
public class Doctor {
    @Id
            @SequenceGenerator(
                    name = "doctor_sequence",
                    sequenceName = "doctor_sequence",
                    allocationSize = 1
            )
            @GeneratedValue(
                    strategy = GenerationType.SEQUENCE,
                    generator = "doctor_sequence"
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
    private List<Timetable> timetable;

    @OneToMany(mappedBy = "doctor")
    private List<PatientRecord> records;

    public Doctor(String name, String surname, String patronymic, String specialization, String work_experiences) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
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

    public void setTimetable(List<Timetable> timetable) {
        this.timetable = timetable;
    }

    public List<Timetable> getTimetable() {
        return timetable;
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
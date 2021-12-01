package com.sbkm.hospital;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="PatientRecord")
public class PatientRecord {
    @Id
    @SequenceGenerator(
            name = "patient_record_sequence",
            sequenceName = "patient_record_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_record_sequence"
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(
            name = "date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate date_of_receipt;
    @Column(
            name = "record",
            columnDefinition = "TEXT"
    )
    private String record;

    public PatientRecord() {
    }

    public PatientRecord(Patient patient_id, Doctor doctor_id, LocalDate date_of_receipt, String record) {
        this.patient = patient_id;
        this.doctor = doctor_id;
        this.date_of_receipt = date_of_receipt;
        this.record = record;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatient() {
        return patient.getId();
    }

    public void setPatient(Long patient_id) {
        this.patient.setId(patient_id);
    }

    public Long getDoctor() {
        return doctor.getId();
    }

    public void setDoctor(Long doctor_id) {
        this.doctor.setId(doctor_id);
    }

    public LocalDate getDate_of_receipt() {
        return date_of_receipt;
    }

    public void setDate_of_receipt(LocalDate date_of_receipt) {
        this.date_of_receipt = date_of_receipt;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}

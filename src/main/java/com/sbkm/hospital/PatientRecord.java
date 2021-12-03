package com.sbkm.hospital;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity(name="PatientRecord")
@NamedQuery(name = "PatientRecord.viewRecords",
        query = "select d.name,d.surname,d.patronymic,pr.date_of_receipt,pr.record " +
                "from PatientRecord pr " +
                "left join Doctor d on d.id = pr.doctor_id " +
                "where pr.patient_id = ?1 " +
                "group by d.name,d.surname,d.patronymic,pr.date_of_receipt,pr.record")
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
    @Column(
            name = "patient_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long patient_id;
    @Column(
            name = "doctor_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long doctor_id;
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

    public PatientRecord(Long patient_id, Long doctor_id, LocalDate date_of_receipt, String record) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.date_of_receipt = date_of_receipt;
        this.record = record;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
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

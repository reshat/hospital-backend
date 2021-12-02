package com.sbkm.hospital;

import java.time.LocalDate;

public class PatientRecordDto {
    private Long doctor_id;
    private Long patient_id;
    private String record;
    private LocalDate date_of_receipt;

    public PatientRecordDto() {
    }

    public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.record = record;
        this.date_of_receipt = date_of_receipt;
    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Long getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(Long patient_id) {
        this.patient_id = patient_id;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public LocalDate getDate_of_receipt() {
        return date_of_receipt;
    }

    public void setDate_of_receipt(LocalDate date_of_receipt) {
        this.date_of_receipt = date_of_receipt;
    }
}

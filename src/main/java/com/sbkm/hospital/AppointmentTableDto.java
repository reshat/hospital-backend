package com.sbkm.hospital;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentTableDto {
    private Long patient_id;
    private Long doctor_id;
    private LocalDate date_of_receipt;
    private LocalTime time_of_receipt;

    public AppointmentTableDto() {
    }

    public AppointmentTableDto(Long patient_id, Long doctor_id, LocalDate date_of_receipt, LocalTime time_of_receipt) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.date_of_receipt = date_of_receipt;
        this.time_of_receipt = time_of_receipt;
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

    public LocalTime getTime_of_receipt() {
        return time_of_receipt;
    }

    public void setTime_of_receipt(LocalTime time_of_receipt) {
        this.time_of_receipt = time_of_receipt;
    }

}

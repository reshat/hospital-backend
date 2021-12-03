package com.sbkm.hospital;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "AppointmentTable")
//@NamedQuery(name = "AppointmentTable.checkAppointments",
//        query = "select count(patient_id) " +
//                "from AppointmentTable " +
//                "where doctor_id = ?1 " +
//                " " +
//                "group by doctor_id,surname_n_initials,date_of_receipt,receipt_start,receipt_end")
public class AppointmentTable {
    @Id
    @SequenceGenerator(
            name = "appointment_table_sequence",
            sequenceName = "appointment_table_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "appointment_table_sequence"
    )
    private Long id;
    @Column(
            name = "patient_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long patientId;
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
            name = "time",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime time_of_receipt;
    @Column(
            name = "duration",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime appointment_duration;

    public AppointmentTable() {
    }

    public AppointmentTable(Long patient_id, Long doctor_id, LocalDate date_of_receipt, LocalTime time_of_receipt, LocalTime appointment_duration) {
        this.patientId = patient_id;
        this.doctor_id = doctor_id;
        this.date_of_receipt = date_of_receipt;
        this.time_of_receipt = time_of_receipt;
        this.appointment_duration = appointment_duration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patient_id) {
        this.patientId = patient_id;
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

    public LocalTime getAppointment_duration() {
        return appointment_duration;
    }

    public void setAppointment_duration(LocalTime appointment_duration) {
        this.appointment_duration = appointment_duration;
    }
}

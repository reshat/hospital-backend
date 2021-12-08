package com.sbkm.hospital;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "AppointmentTable")
@NamedQueries({
        @NamedQuery(name = "AppointmentTable.getFreeSlots",
                query = "select  timeOfReceipt " +
                        "from AppointmentTable " +
                        "where doctorId = ?1 " +
                        "and patientId IS NULL " +
                        "group by timeOfReceipt"),
        @NamedQuery(name = "AppointmentTable.makeAnAppointment",
                query = "update AppointmentTable " +
                        "set patientId = ?1 " +
                        "where doctorId = ?2 " +
                        "and dateOfReceipt = ?3 " +
                        "and timeOfReceipt = ?4")
})

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
            name = "patientId",
            columnDefinition = "BIGINT"
    )
    private Long patientId;
    @Column(
            name = "doctorId",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long doctorId;
    @Column(
            name = "dateOfReceipt",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate dateOfReceipt;
    @Column(
            name = "timeOfReceipt",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime timeOfReceipt;
    @Column(
            name = "duration",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime appointmentDuration;

    @ManyToOne
    @JoinColumn(name = "patientId", insertable = false, updatable = false)
    //@JsonBackReference
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "doctorId", insertable = false, updatable = false)
    //@JsonBackReference
    private Doctor doctor;

    public AppointmentTable() {
    }

    public AppointmentTable(Long doctor_id, LocalDate date_of_receipt, LocalTime time_of_receipt, LocalTime appointment_duration) {
        //this.patientId = patient_id;
        this.doctorId = doctor_id;
        this.dateOfReceipt = date_of_receipt;
        this.timeOfReceipt = time_of_receipt;
        this.appointmentDuration = appointment_duration;
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

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctor_id) {
        this.doctorId = doctor_id;
    }

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDate date_of_receipt) {
        this.dateOfReceipt = date_of_receipt;
    }

    public LocalTime getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public void setTimeOfReceipt(LocalTime time_of_receipt) {
        this.timeOfReceipt = time_of_receipt;
    }

    public LocalTime getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(LocalTime appointment_duration) {
        this.appointmentDuration = appointment_duration;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "AppointmentTable{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctor_id=" + doctorId +
                ", date_of_receipt=" + dateOfReceipt +
                ", time_of_receipt=" + timeOfReceipt +
                ", appointment_duration=" + appointmentDuration +
                '}';
    }
}

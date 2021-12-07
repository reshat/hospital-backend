package com.sbkm.hospital;


import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "Timetable")
@IdClass(TimetableID.class)
@NamedQuery(name = "Timetable.getSchedule",
        query = "select doctorId,surname_n_initials,dateOfReceipt,receiptStart,receiptEnd " +
                "from Timetable " +
                "where doctorId = ?1 " +
                "and dateOfReceipt >= current_date " +
                "and dateOfReceipt <= (current_date + 14 - CAST ((extract(isodow from current_date)) as integer)) " +
                "group by doctorId,surname_n_initials,dateOfReceipt,receiptStart,receiptEnd")
@NamedQuery(name = "Timetable.changeTime",
        query = "update Timetable " +
                "set receiptStart = ?3, receiptEnd = ?4 " +
                "where doctorId = ?1 " +
                "and dateOfReceipt = ?2")
public class Timetable {
    @Id
    private Long doctorId;
    @Column(
            name = "surname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String surname_n_initials;
    @Id
    @Column(
            name = "date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate dateOfReceipt;
    @Column(
            name = "receipt_start",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime receiptStart;
    @Column(
            name = "receipt_end",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime receiptEnd;

    @ManyToOne
    @JoinColumn(name = "doctorId", insertable = false, updatable = false)
    private Doctor doctor;

    public Timetable() {
    }

    public Timetable(Doctor doctor, LocalDate date_of_receipt, LocalTime receipt_start, LocalTime receipt_end) {
        this.doctorId = doctor.getId();
        this.surname_n_initials = doctor.getSurname()
                + " " + doctor.getName().charAt(0);
        if(!Objects.equals(doctor.getPatronymic(), ""))
            this.surname_n_initials += "." + doctor.getPatronymic().charAt(0)
                    + ".";
        this.dateOfReceipt = date_of_receipt;
        this.receiptStart = receipt_start;
        this.receiptEnd = receipt_end;

    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctor_id) {
        this.doctorId = doctor_id;
    }

    public String getSurname_n_initials() {return surname_n_initials;}

    public void setSurname_n_initials(String surname_n_initials) {
        this.surname_n_initials = surname_n_initials;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDate date_of_receipt) {
        this.dateOfReceipt = date_of_receipt;
    }

    public LocalTime getReceiptStart() {
        return receiptStart;
    }

    public void setReceiptStart(LocalTime receipt_start) {
        this.receiptStart = receipt_start;
    }

    public LocalTime getReceiptEnd() {
        return receiptEnd;
    }

    public void setReceiptEnd(LocalTime receipt_end) {
        this.receiptEnd = receipt_end;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                ", doctor_id=" + doctorId +
                ", surname_n_initials='" + surname_n_initials + '\'' +
                ", date_of_receipt=" + dateOfReceipt +
                ", receipt_start=" + receiptStart +
                ", receipt_end=" + receiptEnd +
                '}';
    }
}
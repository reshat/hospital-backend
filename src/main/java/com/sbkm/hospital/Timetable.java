package com.sbkm.hospital;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "Timetable")
@NamedQuery(name = "Timetable.getSchedule",
        query = "select doctor_id,surname,date,receipt_start,receipt_end " +
                "from Timetable " +
                "where doctor_id = ?1 " +
                "and date >= current_date " +
                "and date <= (current_date + 14 - extract(isodow from current_date)::int) " +
                "group by doctor_id,surname,receipt_start,receipt_start,receipt_end")
public class Timetable {
    @Id
    @Column(name = "doctor_id")
    private Long id;
    @Column(
            name = "surname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String surname_n_initials;
    @Column(
            name = "date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private java.util.Date date_of_receipt;
    @Column(
            name = "receipt_start",
            nullable = false,
            columnDefinition = "TIME"
    )
    private java.util.Date receipt_start;
    @Column(
            name = "receipt_end",
            nullable = false,
            columnDefinition = "TIME"
    )
    private java.util.Date receipt_end;

    @OneToOne
    @MapsId
    //@JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Timetable() {
    }

    public Timetable(Doctor doctor, Date date_of_receipt, Date receipt_start, Date receipt_end) {
        this.id = doctor.getId();
        this.doctor = doctor;
        this.surname_n_initials = doctor.getSurname()
                + " " + doctor.getName().charAt(0);
        if(!Objects.equals(doctor.getPatronymic(), ""))
        this.surname_n_initials += "." + doctor.getPatronymic().charAt(0)
                + ".";
        this.date_of_receipt = date_of_receipt;
        this.receipt_start = receipt_start;
        this.receipt_end = receipt_end;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname_n_initials() {
        return surname_n_initials;
    }

    public void setSurname_n_initials(String surname_n_initials) {
        this.surname_n_initials = surname_n_initials;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate_of_receipt() {
        return date_of_receipt;
    }

    public void setDate_of_receipt(Date date_of_receipt) {
        this.date_of_receipt = date_of_receipt;
    }

    public Date getReceipt_start() {
        return receipt_start;
    }

    public void setReceipt_start(Date receipt_start) {
        this.receipt_start = receipt_start;
    }

    public Date getReceipt_end() {
        return receipt_end;
    }

    public void setReceipt_end(Date receipt_end) {
        this.receipt_end = receipt_end;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id=" + id +
                ", surname_n_initials='" + surname_n_initials + '\'' +
                ", date_of_receipt=" + date_of_receipt +
                ", receipt_start=" + receipt_start +
                ", receipt_end=" + receipt_end +
                '}';
    }
}

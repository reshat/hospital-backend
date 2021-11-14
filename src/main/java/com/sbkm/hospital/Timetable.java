package com.sbkm.hospital;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "Timetable")
@IdClass(TimetableID.class)
@NamedQuery(name = "Timetable.getSchedule",
        query = "select doctor_id,surname_n_initials,date_of_receipt,receipt_start,receipt_end " +
                "from Timetable " +
                "where doctor_id = ?1 " +
                "and date_of_receipt >= current_date " +
                "and date_of_receipt <= (current_date + 14 - CAST ((extract(isodow from current_date)) as integer)) " +
                "group by doctor_id,surname_n_initials,date_of_receipt,receipt_start,receipt_end")
public class Timetable {
    @Id
    private Long doctor_id;
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
    private LocalDate date_of_receipt;
    @Column(
            name = "receipt_start",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime receipt_start;
    @Column(
            name = "receipt_end",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime receipt_end;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private Doctor doctor;

    public Timetable() {
    }

    public Timetable(Doctor doctor, LocalDate date_of_receipt, LocalTime receipt_start, LocalTime receipt_end) {
        this.doctor_id = doctor.getId();
        this.surname_n_initials = doctor.getSurname()
                + " " + doctor.getName().charAt(0);
        if(!Objects.equals(doctor.getPatronymic(), ""))
        this.surname_n_initials += "." + doctor.getPatronymic().charAt(0)
                + ".";
        this.date_of_receipt = date_of_receipt;
        this.receipt_start = receipt_start;
        this.receipt_end = receipt_end;

    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
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

    public LocalDate getDate_of_receipt() {
        return date_of_receipt;
    }

    public void setDate_of_receipt(LocalDate date_of_receipt) {
        this.date_of_receipt = date_of_receipt;
    }

    public LocalTime getReceipt_start() {
        return receipt_start;
    }

    public void setReceipt_start(LocalTime receipt_start) {
        this.receipt_start = receipt_start;
    }

    public LocalTime getReceipt_end() {
        return receipt_end;
    }

    public void setReceipt_end(LocalTime receipt_end) {
        this.receipt_end = receipt_end;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                ", doctor_id=" + doctor_id +
                ", surname_n_initials='" + surname_n_initials + '\'' +
                ", date_of_receipt=" + date_of_receipt +
                ", receipt_start=" + receipt_start +
                ", receipt_end=" + receipt_end +
                '}';
    }
}

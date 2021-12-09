package com.sbkm.hospital;



import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity(name = "Timetable")
@IdClass(TimetableID.class)
@NamedQueries({
        @NamedQuery(name = "Timetable.getSchedule",
                query = "select doctor_id,surnameNInitials,workDate,workStart,workEnd " +
                        "from Timetable " +
                        "where doctor_id = ?1 " +
                        "and workDate >= current_date " +
                        "and workDate <= (current_date + 14 - CAST ((extract(isodow from current_date)) as integer)) " +
                        "group by doctor_id,surnameNInitials,workDate,workStart,workEnd"),
        @NamedQuery(name = "Timetable.changeTime",
                query = "update Timetable " +
                        "set workStart = ?3, workEnd = ?4 " +
                        "where doctor_id = ?1 " +
                        "and date = ?2")
})
public class Timetable {
    @Id
    private Long doctor_id;
    @Column(
            name = "surname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String surnameNInitials;
    @Id
    @Column(
            name = "work_date",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate workDate;
    @Column(
            name = "work_start",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime workStart;
    @Column(
            name = "work_end",
            nullable = false,
            columnDefinition = "TIME"
    )
    private LocalTime workEnd;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    //@JsonBackReference
    private Doctor doctor;
//    @OneToMany(mappedBy = "workDate",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    //@JsonManagedReference
//    private List<AppointmentTable> appointmentDates;
//    @OneToMany(mappedBy = "doctor",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true)
//    //@JsonManagedReference
//    private List<AppointmentTable> appointmentDoctors;

    public Timetable() {
    }

    public Timetable(Doctor doctor, LocalDate date_of_receipt, LocalTime receipt_start, LocalTime receipt_end) {
        this.doctor_id = doctor.getId();
        this.surnameNInitials = doctor.getSurname()
                + " " + doctor.getName().charAt(0);
        if(!Objects.equals(doctor.getPatronymic(), ""))
            this.surnameNInitials += "." + doctor.getPatronymic().charAt(0)
                    + ".";
        this.workDate = date_of_receipt;
        this.workStart = receipt_start;
        this.workEnd = receipt_end;

    }

    public Long getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(Long doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getSurnameNInitials() {return surnameNInitials;}

    public void setSurnameNInitials(String surname_n_initials) {
        this.surnameNInitials = surname_n_initials;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDate date_of_receipt) {
        this.workDate = date_of_receipt;
    }

    public LocalTime getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalTime receipt_start) {
        this.workStart = receipt_start;
    }

    public LocalTime getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalTime receipt_end) {
        this.workEnd = receipt_end;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "doctorId=" + doctor_id +
                ", surnameNInitials='" + surnameNInitials + '\'' +
                ", workDate=" + workDate +
                ", workStart=" + workStart +
                ", workEnd=" + workEnd +
                '}';
    }
}
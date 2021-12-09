package com.sbkm.hospital;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="PatientRecord")
@NamedQueries({
        @NamedQuery(name = "PatientRecord.viewRecords",
        query = "select d.name, d.surname, d.patronymic, pr.dateOfReceipt, pr.record " +
                "from PatientRecord pr " +
                "left join Doctor d on d.id = pr.doctorId " +
                "where pr.patientId = ?1 " +
                "group by d.name,d.surname,d.patronymic,pr.dateOfReceipt,pr.record "),
        @NamedQuery(name = "PatientRecord.viewPatients",
        query = "select p.name, p.surname, p.patronymic " +
                "from PatientRecord pr " +
                "left join Patient p on p.id = pr.patientId " +
                "where pr.doctorId = ?1 " +
                "group by p.name, p.surname, p.patronymic")
})
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
    private Long patientId;
    @Column(
            name = "doctor_id",
            nullable = false,
            columnDefinition = "BIGINT"
    )
    private Long doctorId;
    @Column(
            name = "date_of_receipt",
            nullable = false,
            columnDefinition = "DATE"
    )
    private LocalDate dateOfReceipt;
    @Column(
            name = "record",
            columnDefinition = "TEXT"
    )
    private String record;

    @ManyToOne
    @JoinColumn(name = "patient_id", insertable = false, updatable = false)
    //@JsonBackReference
    private Patient patient;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "doctor_id", referencedColumnName="doctor_id", insertable = false, updatable = false),
            @JoinColumn(name = "date_of_receipt", referencedColumnName="work_date", insertable = false, updatable = false)
    })
    //@JsonBackReference
    private Timetable timetableId;

    public PatientRecord() {
    }

    public PatientRecord(Long patient_id, Long doctor_id, LocalDate date_of_receipt, String record) {
        this.patientId = patient_id;
        this.doctorId = doctor_id;
        this.dateOfReceipt = date_of_receipt;
        this.record = record;
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

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Timetable getTimetableId() {
        return timetableId;
    }

    public void setTimetableId(Timetable workDate) {
        this.timetableId = workDate;
    }

    @Override
    public String toString() {
        return "PatientRecord{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", dateOfReceipt=" + dateOfReceipt +
                ", record='" + record + '\'' +
                '}';
    }
}

package com.sbkm.hospital;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

public class Calendar {
    private Long doctorId;
    private String surnameNInitials;
    private String workDate;
    private String workStart;
    private String workEnd;

    public Calendar() {
    }

    public Calendar(Long doctorId, String surnameNInitials, String workDate, String workStart, String workEnd) {
        this.doctorId = doctorId;
        this.surnameNInitials = surnameNInitials;
        this.workDate = workDate;
        this.workStart = workStart;
        this.workEnd = workEnd;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getSurnameNInitials() {
        return surnameNInitials;
    }

    public void setSurnameNInitials(String surnameNInitials) {
        this.surnameNInitials = surnameNInitials;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public String getWorkStart() {
        return workStart;
    }

    public void setWorkStart(String workStart) {
        this.workStart = workStart;
    }

    public String getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(String workEnd) {
        this.workEnd = workEnd;
    }
}

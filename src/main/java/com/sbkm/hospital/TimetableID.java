package com.sbkm.hospital;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TimetableID implements Serializable {
    private Long doctorId;
    private LocalDate workDate;

    public TimetableID() {
    }

    public TimetableID(Long doctor_id, LocalDate date_of_receipt) {
        this.doctorId = doctor_id;
        this.workDate = date_of_receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimetableID that = (TimetableID) o;
        return doctorId.equals(that.doctorId) && workDate.equals(that.workDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, workDate);
    }
}

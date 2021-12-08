package com.sbkm.hospital;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class TimetableID implements Serializable {
    private Long doctor_id;
    private LocalDate workDate;

    public TimetableID() {
    }

    public TimetableID(Long doctor_id, LocalDate date_of_receipt) {
        this.doctor_id = doctor_id;
        this.workDate = date_of_receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimetableID that = (TimetableID) o;
        return doctor_id.equals(that.doctor_id) && workDate.equals(that.workDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctor_id, workDate);
    }
}

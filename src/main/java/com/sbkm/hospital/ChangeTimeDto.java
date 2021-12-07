package com.sbkm.hospital;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class ChangeTimeDto implements Serializable {
    private Long doctorId;
    private LocalDate dateOfReceipt;
    private LocalTime receiptStart;
    private LocalTime receiptEnd;

    public ChangeTimeDto(Long doctorId, LocalDate dateOfReceipt, LocalTime receiptStart, LocalTime receiptEnd) {
        this.doctorId = doctorId;
        this.dateOfReceipt = dateOfReceipt;
        this.receiptStart = receiptStart;
        this.receiptEnd = receiptEnd;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDate dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public LocalTime getReceiptStart() {
        return receiptStart;
    }

    public void setReceiptStart(LocalTime receiptStart) {
        this.receiptStart = receiptStart;
    }

    public LocalTime getReceiptEnd() {
        return receiptEnd;
    }

    public void setReceiptEnd(LocalTime receiptEnd) {
        this.receiptEnd = receiptEnd;
    }
}

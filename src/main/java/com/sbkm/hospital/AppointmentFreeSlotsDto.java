package com.sbkm.hospital;

import java.time.LocalDate;

public class AppointmentFreeSlotsDto {
    private Long id;
    private LocalDate date;

    public AppointmentFreeSlotsDto() {
    }

    public AppointmentFreeSlotsDto(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

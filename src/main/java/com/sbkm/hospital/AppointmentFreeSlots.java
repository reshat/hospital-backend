package com.sbkm.hospital;

public class AppointmentFreeSlots {
    private String timeOfReceipt;

    public AppointmentFreeSlots() {
    }

    public AppointmentFreeSlots(String timeOfReceipt) {
        this.timeOfReceipt = timeOfReceipt;
    }

    public String getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public void setTimeOfReceipt(String timeOfReceipt) {
        this.timeOfReceipt = timeOfReceipt;
    }
}

package com.sbkm.hospital;

public enum Permission {
    PATIENT_READ("patient:read"),
    DOCTOR_WRITE("doctor:write"),
    DOCTOR_READ("doctor:read");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }


    public String getPermission() {
        return permission;
    }
}

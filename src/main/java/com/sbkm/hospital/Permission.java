package com.sbkm.hospital;

public enum Permission {
    PATIENT_READ("patient:read"),
    PATIENT_WRITE("patient:write"),
    DOCTOR_WRITE("doctor:write"),
    DOCTOR_READ("doctor:read"),
    ADMIN_WRITE("admin:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }


    public String getPermission() {
        return permission;
    }
}

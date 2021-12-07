package com.sbkm.hospital;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    PATIENT(Set.of(Permission.PATIENT_READ, Permission.PATIENT_WRITE)),
    DOCTOR(Set.of(Permission.DOCTOR_READ,Permission.DOCTOR_WRITE,Permission.PATIENT_READ, Permission.PATIENT_WRITE)),
    ADMIN(Set.of(Permission.DOCTOR_READ,Permission.DOCTOR_WRITE,Permission.PATIENT_READ, Permission.PATIENT_WRITE, Permission.ADMIN_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

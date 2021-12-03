package com.sbkm.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentTableRepository extends JpaRepository<AppointmentTable, Long> {
    Long countByPatientId(Long patientId);
}

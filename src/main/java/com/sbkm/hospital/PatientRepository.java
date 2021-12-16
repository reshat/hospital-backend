package com.sbkm.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Transactional
    @Modifying
    void changeInfo (Long patient_id, String name, String surname, String patronymic, LocalDate birthDate);
}

package com.sbkm.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long>{
    Iterable<ViewRecords> viewRecords(Long id);
    Iterable<ViewPatients> viewPatients(Long id);
}

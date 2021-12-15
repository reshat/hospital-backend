package com.sbkm.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentTableRepository extends JpaRepository<AppointmentTable, Long> {
    Long countByPatientId(Long patientId);
    Iterable<PatientDto> getPatientList(Long doctor_id);
    Iterable<AppointmentFreeSlots> getFreeSlots(Long doctor_id);
    Iterable<AppointmentInfo> getAppointmentInfo(Long patient_id);
    @Transactional
    @Modifying
    void makeAnAppointment (Long patient_id, Long doctor_id, LocalDate date_of_receipt, LocalTime time_of_receipt);
    boolean existsByDoctorIdAndDateOfReceiptAndTimeOfReceipt(Long doctor_id, LocalDate date_of_receipt, LocalTime time_of_receipt);
    //boolean existsByDoctorIdAndDateOfReceiptAndTimeOfReceiptLessThanEqualAndReceiptEndGreaterThanEqual(Long doctor_id, LocalDate date_of_receipt, LocalTime time_start, LocalTime time_end);
}

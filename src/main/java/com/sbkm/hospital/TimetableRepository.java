package com.sbkm.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Iterable<Timetable> getSchedule (Long id);
    boolean existsByDoctorIdAndDateOfReceipt(Long doctor_id, LocalDate date_of_receipt);
    boolean existsByDoctorIdAndDateOfReceiptAndReceiptStartLessThanEqualAndReceiptEndGreaterThanEqual(Long doctor_id, LocalDate date_of_receipt, LocalTime time_start, LocalTime time_end);
}
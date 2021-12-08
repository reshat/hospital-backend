package com.sbkm.hospital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Iterable<Calendar> getSchedule (Long id);
    @Transactional
    @Modifying
    void changeTime (Long doctorId, LocalDate dateOfReceipt, LocalTime receiptStart, LocalTime receiptEnd);
    boolean existsByDoctorIdAndWorkDate(Long doctor_id, LocalDate date_of_receipt);
    //boolean existsByDoctorIdAndDateOfReceiptAndReceiptStartLessThanEqualAndReceiptEndGreaterThanEqual(Long doctor_id, LocalDate date_of_receipt, LocalTime time_start, LocalTime time_end);
}
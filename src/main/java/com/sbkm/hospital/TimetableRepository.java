package com.sbkm.hospital;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Iterable<Timetable> getSchedule (Long id);
    //Iterable<Timetable> findAllByIdAndDate_of_receiptGreaterThanEqual (Long id);
}
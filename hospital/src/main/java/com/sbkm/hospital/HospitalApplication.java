package com.sbkm.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Time;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(DoctorRepository doctorRepository, TimetableRepository timetableRepository) {
        return args -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            // Use date_trunc(field, source [, time_zone ])
            // when selecting from timetable
            Doctor test_doctor = new Doctor(
                    "Иван",
                    "Иванов",
                    "Иванович");
            Timetable test_table = new Timetable(
                    test_doctor,
                    dateFormat.parse("1990-01-01"),
                    timeFormat.parse("10:30"),
                    timeFormat.parse("18:00"));
            timetableRepository.save(test_table);
            // Order of save matters!!!
            doctorRepository.save(test_doctor);
        };
    }
//    @Bean
//    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
//        return args -> {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            Patient test_patient = new Patient(
//                    "Иван",
//                    "Иванов",
//                    "Иванович",
//                    dateFormat.parse("1990-01-01"));
//            patientRepository.save(test_patient);
//        };
//    }
}
package com.sbkm.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(DoctorRepository doctorRepository) {
//        return args -> {
//            Doctor test_doctor = new Doctor(
//                    "Иван",
//                    "Иванов",
//                    "Иванович");
//            doctorRepository.save(test_doctor);
//        };
//    }
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            Patient test_patient = new Patient(
                    "Иван",
                    "Иванов",
                    "Иванович",
                    Date.valueOf("1990-01-01"));
            patientRepository.save(test_patient);
        };
    }
}

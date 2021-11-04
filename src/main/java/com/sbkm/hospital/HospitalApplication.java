package com.sbkm.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(DoctorRepository doctorRepository) {
        return args -> {
            Doctor test_doctor = new Doctor(
                    "Иван",
                    "Иванов",
                    "Иванович");
            doctorRepository.save(test_doctor);
        };
    }
}

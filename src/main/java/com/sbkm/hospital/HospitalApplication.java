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
    CommandLineRunner commandLineRunner(DoctorRepository doctorRepository, UserRepository userRepository) {
        return args -> {
            Doctor test_doctor = new Doctor(
                    "Иван",
                    "Иванов",
                    "Иванович");
            doctorRepository.save(test_doctor);
            User test_user = new User(
                    "user",
                    "$2a$12$h8nWjz6cz08vpxC3KCd2leRIh5GGvjghd1pQRfi3wnJazvvpvZn0i",
                    Role.PATIENT);
            userRepository.save(test_user);
        };
    }


}

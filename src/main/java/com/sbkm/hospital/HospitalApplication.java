package com.sbkm.hospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(DoctorRepository doctorRepository, TimetableRepository timetableRepository) {
//        return args -> {
//            Doctor test_doctor = new Doctor(
//                    "Иван",
//                    "Иванов",
//                    "Иванович",
//                    "",
//                    "");
//// Order of save matters!!!
//            doctorRepository.save(test_doctor);
//
//            Timetable test_table = new Timetable(
//                    test_doctor,
//                    LocalDate.parse("2021-11-14"),
//                    LocalTime.parse("10:30"),
//                    LocalTime.parse("18:00"));
////            test_doctor.getTimetable().add(test_table);
//            timetableRepository.save(test_table);
//            Timetable test_table1 = new Timetable(
//                    test_doctor,
//                    LocalDate.parse("2021-11-21"),
//                    LocalTime.parse("10:30"),
//                    LocalTime.parse("18:00"));
////            test_doctor.getTimetable().add(test_table1);
//            timetableRepository.save(test_table1);
//            Timetable test_table2 = new Timetable(
//                    test_doctor,
//                    LocalDate.parse("2021-11-22"),
//                    LocalTime.parse("10:30"),
//                    LocalTime.parse("18:00"));
////            test_doctor.getTimetable().add(test_table2);
//            timetableRepository.save(test_table2);
//        };
    /*@Bean
    CommandLineRunner commandLineRunner(DoctorRepository doctorRepository, UserRepository userRepository) {
        return args -> {
            Doctor test_doctor = new Doctor(
                    "Иван",
                    "Иванов",
                    "Иванович",
                    "",
                    "");
            doctorRepository.save(test_doctor);
            User test_user = new User(
                    "user",
                    "$2a$12$h8nWjz6cz08vpxC3KCd2leRIh5GGvjghd1pQRfi3wnJazvvpvZn0i",
                    Role.PATIENT);
            userRepository.save(test_user);
        };
    }*/


}

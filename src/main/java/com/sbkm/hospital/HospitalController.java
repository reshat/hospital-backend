package com.sbkm.hospital;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class HospitalController {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    HospitalController(DoctorRepository repository, UserRepository userRepository) {
        this.doctorRepository = repository;
        this.userRepository = userRepository;
    }

    @GetMapping("/test")
    @CrossOrigin("http://localhost:3000")
    @PreAuthorize("hasAuthority('patient:read')")
    String test() {
        System.out.println("test mapping incoming");
        return "answer to test string";
    }
    @GetMapping("/general")
    @CrossOrigin("http://localhost:3000")
    String general() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        GeneralInformation data = new GeneralInformation();
        String result = objectMapper.writeValueAsString(data);
        return result;
    }
    @GetMapping("/doctors")
    @CrossOrigin("http://localhost:3000")
    public @ResponseBody
    Iterable<Doctor> getAllDoctor() {
        Iterable<Doctor> doctors = doctorRepository.findAll();
        return doctors;
    }
    @GetMapping("/testAdd")
    @CrossOrigin("http://localhost:3000")
    void testAdd() {
        User test_user = new User(
                "user",
                "$2a$12$h8nWjz6cz08vpxC3KCd2leRIh5GGvjghd1pQRfi3wnJazvvpvZn0i",
                Role.PATIENT);
        userRepository.save(test_user);
    }

}

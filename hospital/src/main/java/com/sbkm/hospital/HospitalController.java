package com.sbkm.hospital;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;

@RestController
public class HospitalController {
    private final DoctorRepository repository;

    HospitalController(DoctorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    String test() {
        System.out.println("test mapping incoming");
        return "answer to test string";
    }
    @GetMapping("/general")
    String general() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        GeneralInformation data = new GeneralInformation();
        String result = objectMapper.writeValueAsString(data);
        return result;
    }
}

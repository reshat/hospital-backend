package com.sbkm.hospital;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HospitalControllerTest {

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void general() {
                ResponseEntity<String> result = template
                .getForEntity("/general", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void registerUser() throws Exception {
        ResultActions ra = mockMvc.perform(post("/signup")
                .flashAttr("signUpDto", new SignUpDto("Test2","Test2","Test2","test2","test2@test.com","test2")));
        MvcResult mr = ra.andReturn();
        assertThat(mr.getResponse().getStatus()).isIn(200, 400);
    }

    @Test
    void getAllDoctor() {
    }

    @Test
    void patientRecord() {
    }

    @Test
    void getDoctorSchedule() {
    }

    @Test
    void getPatientRecord() {
    }
}
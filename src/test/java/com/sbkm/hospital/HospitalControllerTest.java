package com.sbkm.hospital;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HospitalControllerTest {

    @Autowired
    HospitalController hc;
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void general() {
                ResponseEntity<String> result = template
                .getForEntity("/general", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    @Order(2)
    public void registerUser() throws Exception {
        ResultActions ra = mockMvc.perform(post("/signup")
                .flashAttr("signUpDto", new SignUpDto("Олег","Тинькоф","Леонидович","user","doctor@test.com","user")));
        MvcResult mr = ra.andReturn();
        assertEquals(200, mr.getResponse().getStatus());

    }
//    @Test
//    @Order(3)
//    public void registerDoctor() throws Exception {
//        System.out.println("Doctor");
//        String result = hc.registerDoctor(new SignUpDto("Ива","Ванг","Семенович","doctor","dddd@test.com","doctor"));
//        assertEquals("User registered successfully",result);
//    }
    @Test
    @Order(4)
    void getAllDoctor() {
        ResponseEntity<String> result = template
                .getForEntity("/doctors", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    @Order(5)
    public void basicAuth() throws Exception {
        this.mockMvc
                .perform(post("/login").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("user:user".getBytes())))
                .andExpect(status().isOk());
    }
    @Test
    @Order(5)
    public void basicAuthFail() throws Exception {
        this.mockMvc
                .perform(post("/login").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("useer:user".getBytes())))
                .andExpect(status().is4xxClientError());
    }
//    @Test
//    @Order(6)
//    void patientRecord() throws Exception {
//        //patientRecord
//        System.out.println(LocalDate.of(2021,12,7));
//        ResultActions ra = mockMvc.perform(post("/patientRecord")
//                .flashAttr("patientRecordDto", new PatientRecordDto(1L,1L,"Все хорошо",LocalDate.of(2021,12,7))).header(HttpHeaders.AUTHORIZATION,
//                        "Basic " + Base64Utils.encodeToString("doctor:doctor".getBytes())));
//        //public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
//            MvcResult mr = ra.andReturn();
//        assertThat(mr.getResponse().getStatus()).isIn(200, 400);
//    }
    @Test
    @Order(7)
    void testRequest() throws Exception {
        //patientRecord
        ResultActions ra = mockMvc.perform(get("/test").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("user:user".getBytes())));
        //public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
        MvcResult mr = ra.andReturn();
        assertThat(mr.getResponse().getStatus()).isIn(200, 400);
    }
    @Test
    @Order(7)
    void testRequestFail() throws Exception {
        //patientRecord
        ResultActions ra = mockMvc.perform(get("/test").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("useer:user".getBytes())));
        //public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
        MvcResult mr = ra.andReturn();
        assertEquals(mr.getResponse().getStatus(),401);
    }
    //@GetMapping("/calendar")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//public @ResponseBody
//Iterable<Calendar> getDoctorSchedule(@RequestParam String id) throws Exception {
//    if(!doctorRepository.existsById(Long.valueOf(id))){
//        throw new Exception("Unknown Doctor ID");
//    }
//    Iterable<Calendar> tt = generalService.getSchedule(Long.valueOf(id));
//    return tt;
//}

    @Test
    void getCalendar() throws Exception {
        this.mockMvc
                .perform(get("/calendar").param("id","1"))
                .andExpect(status().isOk());
    }
//    @Test
//    @Order(8)
//    void viewPatients() throws Exception {
//
//        ResultActions ra = mockMvc.perform(get("/viewPatients")
//                .flashAttr("id", 1).header(HttpHeaders.AUTHORIZATION,
//                        "Basic " + Base64Utils.encodeToString("doctor:doctor".getBytes())));
//        //public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
//        MvcResult mr = ra.andReturn();
//        assertThat(mr.getResponse().getStatus()).isIn(200, 400);
//    }
//    @Test
//    @Order(9)
//    public void registerAdmin() throws Exception {
//        String result = hc.registerAdmin(new SignUpDto("Ива","Ванг","Семенович","doctor","dddd@test.com","doctor"));
//        assertEquals("User registered successfully",result);
//    }
    //viewPatients
//
//    @Test
//    void getDoctorSchedule() {
//        ResponseEntity<String> result = template
//                .getForEntity("/calendar", String.class,1);
//        assertEquals(HttpStatus.OK, result.getStatusCode());
//    }

}
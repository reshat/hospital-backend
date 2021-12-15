package com.sbkm.hospital;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts="classpath:myData.sql")
@Transactional
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
                .flashAttr("signUpDto", new SignUpDto("Олег","Тинькоф","Леонидович","user1","us1er@test.com","pass")));
        MvcResult mr = ra.andReturn();
        assertEquals(200, mr.getResponse().getStatus());

    }

    @Test
    void getAllDoctor() {
        ResponseEntity<String> result = template
                .getForEntity("/doctors", String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
    @Test
    public void basicAuth() throws Exception {
        this.mockMvc
                .perform(post("/login").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("doctor:pass".getBytes())))
                .andExpect(status().isOk());
    }
    @Test
    public void basicAuthFail() throws Exception {
        this.mockMvc
                .perform(post("/login").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("useer:pass".getBytes())))
                .andExpect(status().is4xxClientError());
    }
    @Test
    void patientRecord() throws Exception {
        //patientRecord
        System.out.println(LocalDate.of(2021,12,7));
        ResultActions ra = mockMvc.perform(post("/patientRecord")
                .flashAttr("patientRecordDto", new PatientRecordDto(1L,1L,"Все хорошо",LocalDate.of(2021,12,7))).header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("doctor:pass".getBytes())));
        //public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
            MvcResult mr = ra.andReturn();
        assertThat(mr.getResponse().getStatus()).isIn(200, 400);
    }
    @Test
    void testRequest() throws Exception {
        //patientRecord
        ResultActions ra = mockMvc.perform(get("/test").header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("user:pass".getBytes())));
        //public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
        MvcResult mr = ra.andReturn();
        assertThat(mr.getResponse().getStatus()).isIn(200, 400);
    }
    @Test
    void testRequestFail() throws Exception {
        //patientRecord
        ResultActions ra = mockMvc.perform(get("/test").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("useer:pass".getBytes())));
        //public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
        MvcResult mr = ra.andReturn();
        assertEquals(mr.getResponse().getStatus(),401);
    }

    @Test
    void viewPatients() throws Exception {
        ResultActions ra = mockMvc.perform(get("/viewPatients")
                .flashAttr("id", 1).header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString("doctor:pass".getBytes())));
        //public PatientRecordDto(Long doctor_id, Long patient_id, String record, LocalDate date_of_receipt) {
        MvcResult mr = ra.andReturn();
        assertThat(mr.getResponse().getStatus()).isIn(200, 400);
    }


}
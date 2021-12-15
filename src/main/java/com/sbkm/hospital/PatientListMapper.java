package com.sbkm.hospital;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PatientListMapper implements RowMapper<PatientList> {
    @Override
    public PatientList mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatientList patientList = new PatientList();
        patientList.setName(rs.getString("name"));
        patientList.setSurname(rs.getString("surname"));
        patientList.setPatronymic(rs.getString("patronymic"));
        patientList.setDateOfReceipt(rs.getString("date_of_receipt"));
        patientList.setTimeOfReceipt(rs.getString("time_of_receipt"));
        return patientList;

    }
}

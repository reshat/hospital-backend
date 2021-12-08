package com.sbkm.hospital;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewRecordsMapper implements RowMapper<ViewRecords> {
    @Override
    public ViewRecords mapRow(ResultSet rs, int rowNum) throws SQLException {
        ViewRecords viewRecords = new ViewRecords();
        viewRecords.setName(rs.getString("name"));
        viewRecords.setSurname(rs.getString("surname"));
        viewRecords.setPatronymic(rs.getString("patronymic"));
        viewRecords.setDateOfReceipt(rs.getString("dateOfReceipt"));
        viewRecords.setRecord(rs.getString("record"));
        return viewRecords;
    }
}

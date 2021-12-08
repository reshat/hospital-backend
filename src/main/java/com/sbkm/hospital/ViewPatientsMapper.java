package com.sbkm.hospital;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ViewPatientsMapper implements RowMapper<ViewPatients> {
    @Override
    public ViewPatients mapRow(ResultSet rs, int rowNum) throws SQLException {
        ViewPatients viewPatients = new ViewPatients();
        viewPatients.setName(rs.getString("name"));
        viewPatients.setSurname(rs.getString("surname"));
        viewPatients.setPatronymic(rs.getString("patronymic"));
        return viewPatients;
    }
}

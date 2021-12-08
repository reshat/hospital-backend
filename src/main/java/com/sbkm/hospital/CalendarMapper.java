package com.sbkm.hospital;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CalendarMapper implements RowMapper<Calendar> {

    @Override
    public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
        Calendar calendar = new Calendar();
        calendar.setDoctorId(rs.getLong("doctor_id"));
        calendar.setSurnameNInitials(rs.getString("surname"));
        calendar.setWorkDate(rs.getString("work_date"));
        calendar.setWorkStart(rs.getString("work_start"));
        calendar.setWorkEnd(rs.getString("work_end"));
        return calendar;
    }
}

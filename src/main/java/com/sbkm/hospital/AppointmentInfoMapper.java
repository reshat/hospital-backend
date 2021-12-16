package com.sbkm.hospital;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppointmentInfoMapper implements RowMapper<AppointmentInfo> {
    @Override
    public AppointmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppointmentInfo appointmentInfo = new AppointmentInfo();
        appointmentInfo.setName(rs.getString("name"));
        appointmentInfo.setSurname(rs.getString("surname"));
        appointmentInfo.setPatronymic(rs.getString("patronymic"));
        appointmentInfo.setDate_of_reciept(rs.getString("date_of_receipt"));
        appointmentInfo.setTime_of_reciept(rs.getString("time_of_receipt"));
        return appointmentInfo;
    }
}

package com.sbkm.hospital;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AppointmentFreeSlotsMapper implements RowMapper<AppointmentFreeSlots> {
    @Override
    public AppointmentFreeSlots mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppointmentFreeSlots appointmentFreeSlots = new AppointmentFreeSlots();
        appointmentFreeSlots.setTimeOfReceipt(rs.getString("time_of_receipt"));
        return appointmentFreeSlots;
    }
}

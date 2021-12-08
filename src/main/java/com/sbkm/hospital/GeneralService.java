package com.sbkm.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class GeneralService {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private CalendarMapper calendarMapper;
    @Autowired
    private AppointmentFreeSlotsMapper appointmentFreeSlotsMapper;
    @Autowired
    private ViewRecordsMapper viewRecordsMapper;
    @Autowired
    private ViewPatientsMapper viewPatientsMapper;
    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private AppointmentTableRepository appointmentTableRepository;
    @Autowired
    private PatientRecordRepository patientRecordRepository;
    private static final String SQL_GET_SCHEDULE =
            "select doctor_id,surname,work_date,work_start,work_end " +
            "from Timetable " +
            "where doctor_id = :doctor_id " +
            "and work_date >= current_date " +
            "and work_date <= (current_date + 14 - CAST ((extract(isodow from current_date)) as integer)) " +
            "group by doctor_id,surname,work_date,work_start,work_end";
    private static final String SQL_GET_FREE_SLOTS =
                    "select  timeOfReceipt " +
                    "from AppointmentTable " +
                    "where doctorId = :doctor_id " +
                    "and patientId IS NULL " +
                    "group by timeOfReceipt";
    private static final String SQL_VIEW_RECORDS =
                    "select d.name, d.surname, d.patronymic, pr.dateOfReceipt, pr.record " +
                    "from PatientRecord pr " +
                    "left join Doctor d on d.id = pr.doctorId " +
                    "where pr.patientId = :patient_id " +
                    "group by d.name,d.surname,d.patronymic,pr.dateOfReceipt,pr.record ";
    private static final String SQL_VIEW_PATIENTS =
                    "select p.name, p.surname, p.patronymic " +
                    "from PatientRecord pr " +
                    "left join Patient p on p.id = pr.patientId " +
                    "where pr.doctorId = :doctor_id " +
                    "group by p.name, p.surname, p.patronymic";
    public Iterable<Calendar> getSchedule(Long doctor_id){
        return jdbcTemplate.query(SQL_GET_SCHEDULE, new MapSqlParameterSource().addValue("doctor_id", doctor_id), calendarMapper);
    }
    public Iterable<AppointmentFreeSlots> getFreeSlots(Long doctor_id){
        return jdbcTemplate.query(SQL_GET_FREE_SLOTS, new MapSqlParameterSource().addValue("doctor_id", doctor_id), appointmentFreeSlotsMapper);
    }
    public Iterable<ViewRecords> viewRecords(Long patient_id){
        return jdbcTemplate.query(SQL_VIEW_RECORDS, new MapSqlParameterSource().addValue("patient_id", patient_id), viewRecordsMapper);
    }
    public Iterable<ViewPatients> viewPatients(Long doctor_id){
        return jdbcTemplate.query(SQL_VIEW_PATIENTS, new MapSqlParameterSource().addValue("doctor_id", doctor_id), viewPatientsMapper);
    }
}

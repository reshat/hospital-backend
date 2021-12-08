package com.sbkm.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class GeneralService {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final CalendarMapper calendarMapper;
    private final AppointmentFreeSlotsMapper appointmentFreeSlotsMapper;
    private final ViewRecordsMapper viewRecordsMapper;
    private final ViewPatientsMapper viewPatientsMapper;
    private final TimetableRepository timetableRepository;
    private final AppointmentTableRepository appointmentTableRepository;
    private final PatientRecordRepository patientRecordRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    @Autowired
    public GeneralService(NamedParameterJdbcTemplate jdbcTemplate, CalendarMapper calendarMapper, AppointmentFreeSlotsMapper appointmentFreeSlotsMapper, ViewRecordsMapper viewRecordsMapper, ViewPatientsMapper viewPatientsMapper, TimetableRepository timetableRepository, AppointmentTableRepository appointmentTableRepository, PatientRecordRepository patientRecordRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.calendarMapper = calendarMapper;
        this.appointmentFreeSlotsMapper = appointmentFreeSlotsMapper;
        this.viewRecordsMapper = viewRecordsMapper;
        this.viewPatientsMapper = viewPatientsMapper;
        this.timetableRepository = timetableRepository;
        this.appointmentTableRepository = appointmentTableRepository;
        this.patientRecordRepository = patientRecordRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }
    private static final String SQL_GET_SCHEDULE =
            "select doctor_id,surname,work_date,work_start,work_end " +
            "from Timetable " +
            "where doctor_id = :doctor_id " +
            "and work_date >= current_date " +
            "and work_date <= (current_date + 14 - CAST ((extract(isodow from current_date)) as integer)) " +
            "group by doctor_id,surname,work_date,work_start,work_end";
    private static final String SQL_GET_FREE_SLOTS =
                    "select  time_of_receipt " +
                    "from Appointment_Table " +
                    "where doctor_id = :doctor_id " +
                    "and patient_id IS NULL " +
                    "group by time_of_receipt";
    private static final String SQL_VIEW_RECORDS =
                    "select d.name, d.surname, d.patronymic, pr.date_of_receipt, pr.record " +
                    "from Patient_Record pr " +
                    "left join Doctor d on d.id = pr.doctor_id " +
                    "where pr.patient_id = :patient_id " +
                    "group by d.name,d.surname,d.patronymic,pr.date_of_receipt,pr.record ";
    private static final String SQL_VIEW_PATIENTS =
                    "select p.name, p.surname, p.patronymic " +
                    "from Patient_Record pr " +
                    "left join Patient p on p.id = pr.patient_id " +
                    "where pr.doctor_id = :doctor_id " +
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

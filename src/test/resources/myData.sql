insert into users(login, password, role,email) values ('doctor','$2a$10$fSGkSrOij5NCMS67/bW9tuyclKn127DOvAZQvPnPZu0dwvDwtBa2G', 'DOCTOR','email@s.com');
insert into doctor(id,name, surname, patronymic, specialization, work_experiences) values (1,'Иван', 'Иванов', 'Иванович', 'Хирург', '5');

insert into users(login, password, role,email) values ('user','$2a$10$fSGkSrOij5NCMS67/bW9tuyclKn127DOvAZQvPnPZu0dwvDwtBa2G', 'PATIENT','emailP@s.com');
insert into patient(id, name, surname, patronymic, birth_date) values(2, 'Иван', 'Иванов', 'Иванович', '1999-01-01');

insert into timetable(doctor_id, work_date, surname, work_start, work_end) values(1, '2021-12-17', 'Иванов И.И.', '09:00', '15:00');
insert into appointment_table(id,patient_id, doctor_id, date_of_receipt, time_of_receipt, duration) values(1,2, 1, '2021-12-17', '10:00', '01:00');
insert into patient_record(id,patient_id, doctor_id, date_of_receipt, record) values(1,2, 1, '2021-12-17', 'пример записи');
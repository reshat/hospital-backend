DROP TABLE IF EXISTS doctor;

create table doctor
(
    id_order serial not null,
    name text not null,
    surname text not null,
    patronymic text
);
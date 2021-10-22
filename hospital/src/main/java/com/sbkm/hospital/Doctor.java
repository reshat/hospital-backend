package com.sbkm.hospital;

import javax.persistence.*;

@Entity
public class Doctor {
    @Id
            @SequenceGenerator(
                    name = "doctor_sequence",
                    sequenceName = "doctor_sequence",
                    allocationSize = 1
            )
            @GeneratedValue(
                    strategy = GenerationType.SEQUENCE,
                    generator = "doctor_sequence"
            )
    int id;
    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String name;
    @Column(
            name = "surname",
            nullable = false,
            columnDefinition = "TEXT"
    )
    String surname;
    @Column(
            name = "patronymic",
            columnDefinition = "TEXT"
    )
    String patronymic;


}
//name text not null,
//    surname text not null,
//    patronymic text
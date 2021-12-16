package com.sbkm.hospital;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true, columnDefinition = "TEXT")
    private String login;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

//    @Column(
//            name = "name",
//            nullable = false,
//            columnDefinition = "TEXT"
//    )
//    private String name;
//
//    @Column(
//            name = "surname",
//            nullable = false,
//            columnDefinition = "TEXT"
//    )
//    private String surname;
//
//    @Column(
//            name = "patronymic",
//            nullable = false,
//            columnDefinition = "TEXT"
//    )
//    private String patronymic;

    @Column(
            name = "email",
            nullable = false,
            unique = true,
            columnDefinition = "TEXT"
    )
    private String email;

    public User(String login, String password, Role role, String email) {
        this.login = login;
        this.password = password;
        this.role = role;
//        this.name = name;
//        this.surname = surname;
//        this.patronymic = patronymic;
        this.email = email;
    }

    public User() {
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSurname() {
//        return surname;
//    }
//
//    public void setSurname(String surname) {
//        this.surname = surname;
//    }
//
//    public String getPatronymic() {
//        return patronymic;
//    }
//
//    public void setPatronymic(String patronymic) {
//        this.patronymic = patronymic;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

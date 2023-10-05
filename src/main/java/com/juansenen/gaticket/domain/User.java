package com.juansenen.gaticket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** Clase que define los atributos de los usuario */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(name = "user_mail")
    private String userMail;
    @Column(name = "user_password")
    private String userPassword;
    @Column(name = "user_tip")
    private String userTip;

    @OneToOne(mappedBy = "user")
    private Rol userRol;

    @OneToOne(mappedBy = "user")
    private Department userDepartment;

    @OneToMany(mappedBy = "user")
    private List<Device> userDevices;

}

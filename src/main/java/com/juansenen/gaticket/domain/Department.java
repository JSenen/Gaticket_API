package com.juansenen.gaticket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** Clase que define los departamentos u oficinas donde se encuentran los dispositivos */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private long departemtId;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "department_phone")
    private String departmentPhone;
    @Column(name = "department_mail")
    private String departmentMail;
    @Column(name = "department_city")
    private String departmentCity;

    @OneToMany(mappedBy = "department")
    private List<Device> devices;


}

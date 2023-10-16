package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Departments inside de company")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "DepartmentId", example = "6")
    private long departemtId;
    @Column(name = "department_name")
    @Schema(description = "Department name", example = "sales")
    private String departmentName;
    @Column(name = "department_phone")
    @Schema(description = "Department contact phone", example = "0700047")
    private String departmentPhone;
    @Column(name = "department_mail")
    @Schema(description = "Department contact mail", example = "sales@megacompany.com")
    private String departmentMail;
    @Column(name = "department_city")
    @Schema(description = "City where it is the department", example = "Zaragoza")
    private String departmentCity;

    @OneToMany(mappedBy = "department")
    private List<Device> devices;

    @OneToMany(mappedBy = "userDepartment")
    @JsonIgnore
    private List<User> userList;


}

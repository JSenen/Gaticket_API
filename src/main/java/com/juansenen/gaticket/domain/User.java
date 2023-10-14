package com.juansenen.gaticket.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String userMail;
    @Column(name = "user_password")
    @NotBlank
    private String userPassword;
    @Column(name = "user_tip")
    @NotBlank
    private String userTip;

    @OneToOne
    @JoinColumn(name = "rol_id")
    private Rol userRol;

    @OneToOne
    @JoinColumn(name = "department_id") // Nombre de la columna que hace referencia a Type
    private Department userDepartment;

    @OneToMany(mappedBy = "user")
    private List<Incidences> incidendesUserId;
}

package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "user_rol")
    @NotBlank
    private String userRol = "usuario";

    @ManyToOne
    @JoinColumn(name = "userDepartmentId") // Nombre de la columna que hace referencia
    private Department userDepartment;

    @OneToMany(mappedBy = "user") // Clave principal
    @JsonIgnore //Evitar serializacion infinita
    private List<Incidences> incidendesUserId;
}

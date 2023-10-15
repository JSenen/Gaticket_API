package com.juansenen.gaticket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Clase que define los roles de los usuario */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private long rolId;
    @Column(name = "rol_type")
    private String rolType;

}

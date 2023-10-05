package com.juansenen.gaticket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Clase que define los tipos de dispositivos */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long typeId;
    @Column(name = "type_type")
    private String type;
}

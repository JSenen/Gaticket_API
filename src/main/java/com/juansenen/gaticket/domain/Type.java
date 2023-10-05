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
    @Column(name = "type_id")
    private long typeId;
    @Column(name = "type_type")
    private String type;

    /** Relacion One To One con Device no es necesario marcarla aqui al
     * encontrarse desarrollada en clase Device
     */
}

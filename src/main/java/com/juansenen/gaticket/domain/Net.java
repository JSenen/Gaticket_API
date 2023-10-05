package com.juansenen.gaticket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Clase que define las distintas IP´s de los dispositivos */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "net")
public class Net {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "net_id")
    private long netId;
    @Column(name = "net_ip")
    private String netIp;
}

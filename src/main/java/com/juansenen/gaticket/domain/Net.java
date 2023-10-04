package com.juansenen.gaticket.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "net")
public class Net {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long netId;
    @Column(name = "net_ip")
    private String netIp;
}

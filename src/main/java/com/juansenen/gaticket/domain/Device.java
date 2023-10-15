package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/** Clase que define los dispositivos */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    private long deviceId;
    @Column(name = "device_hd")
    private int deviceHd;
    @Column(name = "device-ram")
    private int deviceRam;
    @Column(name = "device_mac")
    private String deviceMAc;
    @Column(name = "device_serialnumber")
    private String deviceSerial;
    @Column(name = "device_model")
    private String deviceModel;
    @Column(name = "device_date_buy")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date deviceDateBuy;
    @Column(name = "device_date_start")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date deviceDateStart;

    @OneToOne(mappedBy = "device")
    private Net net;

    @ManyToOne
    @JoinColumn(name = "type_id") // Nombre de la columna que hace referencia a Type
    private Type deviceTypeId;

    @OneToMany(mappedBy = "device")
    @JsonIgnore
    private List<Incidences> incidencesList;


    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;


}

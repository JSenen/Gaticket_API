package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** Clase que define los dispositivos */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "device")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}

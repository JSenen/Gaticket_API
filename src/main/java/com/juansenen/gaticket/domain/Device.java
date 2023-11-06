package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
@Schema(description = "All class of devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "DeviceId", example = "12")
    private long deviceId;
    @Column(name = "device_hd")
    @Schema(description = "Device HD capacity", example = "500")
    private int deviceHd;
    @Column(name = "device-ram")
    @Schema(description = "Device RAM memory installed", example = "32")
    private int deviceRam;
    @Column(name = "device_mac")
    @Schema(description = "Device mac identification", example = "13:BG:45:89:99:AC")
    @Size(min = 17, max = 17, message = "La dirección MAC debe tener exactamente 12 caracteres")
    private String deviceMac;
    @Column(name = "device_serialnumber")
    @Schema(description = "Device serial number", example = "CXV5678D")
    private String deviceSerial;
    @Column(name = "device_model")
    @Schema(description = "Device model", example = "Acer TravelMate")
    private String deviceModel;
    @Column(name = "device_date_buy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Device date of buy MM/dd/yyyy", example = "10/12/2018")
    private Date deviceDateBuy;
    @Column(name = "device_date_start")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Device date begin to work or installed", example = "12/02/2019")
    private Date deviceDateStart;
    /*@Lob //Indica campo grande
    @Basic(fetch = FetchType.LAZY) //Carga la imagen de manera diferida
    @Column(name = "device_image")
    @Schema(description = "Device image in byte array format")
    private byte[] deviceImage;*/ //TODO añadir imagen a tabla externa

    @ManyToOne
    @JoinColumn(name = "type_id") // Nombre de la columna que hace referencia a Type
    private Type deviceType;

    @OneToMany(mappedBy = "device")
    @JsonIgnore
    private List<Incidences> incidencesList;

    @ManyToOne
    @JoinColumn(name = "department")
    @JsonIgnore
    private Department department;

    @OneToOne
    @JoinColumn(name = "net_id") // Nombre de la columna que hace referencia a la red
    private Net net; // Relación OneToOne con una red


}

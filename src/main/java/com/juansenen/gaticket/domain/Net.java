package com.juansenen.gaticket.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Clase que define las distintas IP´s de los dispositivos */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "net")
@Schema(description = "Network information of a device")
public class Net {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "net_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "netId", example = "8")
    private long netId;
    @Column(name = "net_ip")
    @Schema(description = "IP", example = "10.53.3.456")
    private String netIp;
    @Column(name = "net_mask")
    @Schema(description = "Mask of network", example = "255.255.255.0")
    private String netMask;
    @Column(name = "net_cdir")
    @Schema(description = "CDIR number", example = "24")
    private String netCdir;
    @Column(name = "net_gateway")
    @Schema(description = "Gateway number", example = "10.52.2.1")
    private String netGateWay;
    @Column(name = "net_status")
    @Schema(description = "Status of the IP", example = "engaged")
    private boolean netStatus;

}

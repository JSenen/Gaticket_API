package com.juansenen.gaticket.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** Clase que define los tipos de dispositivos */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "types")
@Schema(description = "Type of device information")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "TypeId", example = "4")
    private long typeId;
    @Column(name = "type_type")
    @NotBlank
    @Schema(description = "Type of device", example = "Laptop")
    private String typeName;

    @OneToMany(mappedBy = "deviceTypeId") // Una relación de un tipo con varios dispositivos
    private List<Device> devices;


}

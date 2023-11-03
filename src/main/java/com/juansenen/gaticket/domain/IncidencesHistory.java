package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** Tabla de registro historico de incidencias
 * En ella se registran las incidencias que han pasado a estado finish
 * Lo que permite eliminarlas de las tablas y aqui quedaran registradas
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "incidencesHistory")
public class IncidencesHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "history Id", example = "12")
    @Column(name = "history_id")
    private long idhistory;
    @Column(name = "history_tip")
    @Schema(description = "TIP of the user who record incidence", example = "T45678I")
    private String historyTip;
    @Column(name = "history_theme")
    @Schema(description = "Theme of the history incidence", example = "Mi pc se reinicia solo")
    private String historyTheme;
    @Column(name = "history_commit")
    @Schema(description = "Commit of the history incidence", example = "Cada cierto tiempo el pc se reinica sin previo aviso")
    private String historyCommit;
    @Column(name = "history_date")
    @JsonFormat(pattern = "MM/dd/yyyy")
    @Schema(description = "Date when history incidence was fixit", example = "10/10/2023")
    private Date historyDateFinish;
    @Column(name = "history_device")
    @Schema(description = "Device of the history incidence", example = "CZC45678F")
    private String historyDeviceSerial;
    @Column(name = "historyAdmin")
    @Schema(description = "Admin who solved incidence", example = "A58656F")
    private String historyAdmin;



}

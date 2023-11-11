package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "incidences")
public class Incidences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incidence_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "incidenceId", example = "12")
    private long incidencesId;
    @Column(name = "incidence_commit", columnDefinition = "LONGTEXT", length = Integer.MAX_VALUE)
    @Lob//LongText para textos largos en MySQL
    @Schema(description = "Description", example = "My computer has began to go slowly. And I can´t see mp4 videos")
    private String incidenceCommit;
    @Column(name = "incidence_status")
    @Schema(description = "Incidence status", example = "Active" )
    private String incidenceStatus;
    @Column(name = "incidence_date")
    @Schema(description = "Incidence date start", example = "04/11/2023", format = "date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date incidenceDate;
    @Column(name="incidence_datefinish")
    @Schema(description = "Incidence date fix it", example = "06/11/2023", format = "date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date incidenceDateFinish;
    @Column(name = "incidence_theme")
    @Schema(description = "Incidence theme", example = "Computer damage")
    private String incidenceTheme;
    @Column(name = "incidence_adminid")
    @Schema(description = "Admin ID who fix incidence", example = "A58656F")
    private Integer adminId;

    @ManyToOne
    @JoinColumn(name = "device", nullable = true)
    @Schema(description = "The associated device")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "user")
    @Schema(description = "The user who reported de incidence")
    private User user; //Clave de la tabla principal

    @ManyToOne
    @JoinColumn(name = "user_admin_id")
    @Schema(description = "The administrator user who resolved the incidence")
    private User responsable;

    @OneToMany(mappedBy = "incidenciaMessage",cascade = CascadeType.ALL) // Clave principal
    @JsonIgnore //Evitar serializacion infinita
    private List<Messages> messagesList;
}

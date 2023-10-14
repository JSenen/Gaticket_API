package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "incidences")
public class Incidences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "incidence_id")
    private long incidencesId;
    @Column(name = "incidence_commit")
    private String incidenceCommit;
    @Column(name = "incidence_status")
    private boolean incidenceStatus;
    @Column(name = "incidence_date")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date incidenceDate;
    @Column(name="incidence_datefinish")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date incidenceDateFinish;

    @ManyToOne
    @JoinColumn(name = "device")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user; //Clave de la tabla principal
}

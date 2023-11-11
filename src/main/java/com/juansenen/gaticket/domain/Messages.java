package com.juansenen.gaticket.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "messages")
public class Messages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "MessageId", example = "7")
    private long idMenssage;
    @Column(name = "message_commit", columnDefinition = "LONGTEXT", length = Integer.MAX_VALUE)
    @Lob//LongText para textos largos en MySQL
    @Schema(description = "Description", example = "You should restart the computer every day")
    private String messageCommit;

    @ManyToOne
    @JoinColumn(name = "incidencia_id")
    private Incidences incidenciaMessage;

    @ManyToOne
    @JoinColumn(name = "emisor_id")
    private User emisorMessage; // Puede ser Usuario o Administrador


}

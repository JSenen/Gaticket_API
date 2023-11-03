package com.juansenen.gaticket.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** Clase que define los atributos de los usuario */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Schema(description = "User information")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "UserId", example = "2")
    private long userId;
    @Column(name = "user_mail")
    @NotBlank
    @Schema(description = "User email", example = "juanito@gmail.com")
    private String userMail;
    @Column(name = "user_password")
    @NotBlank
    @Schema(description = "User password", example = "xXFG7ujka123")
    private String userPassword;
    @Column(name = "user_tip")
    @NotBlank
    @Schema(description = "User personal number identification", example = "A58656F")
    private String userTip;
    @Column(name = "user_rol")
    @NotBlank
    @Schema(description = "User rol permision on the API", example = "usuario")
    private String userRol;

    @ManyToOne
    @JoinColumn(name = "userDepartmentId") // Nombre de la columna que hace referencia
    @JsonIgnore
    private Department userDepartment;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL) // Clave principal
    @JsonIgnore //Evitar serializacion infinita
    private List<Incidences> incidendesUserId;

    @OneToMany(mappedBy = "responsable")
    @JsonIgnore
    private List<Incidences> incidencesResponsable;
}

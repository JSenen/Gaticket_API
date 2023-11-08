package com.juansenen.gaticket.controllers;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Incidences;
import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.DeviceService;
import com.juansenen.gaticket.service.IncidenceService;
import com.juansenen.gaticket.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Tag(name = "incidence", description = "This controller contains all the endpoints that can manage incidences")
public class IncidenceController {

    private final static Logger logger = LoggerFactory.getLogger(Incidences.class);

    @Autowired
    private IncidenceService incidenceService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    @Operation(
            summary = "Retrieve all incidences",
            description = "Get all incidences on the data base.",
            tags = { "incidence"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @GetMapping("/incidences")
    public ResponseEntity<List<Incidences>> getAll(@Parameter(description = "User number incidences", required = false)
            @RequestParam(name = "userid", defaultValue = "0", required = false) long userid,
            @Parameter(description = "Device number incidences", required = false)
            @RequestParam(name = "deviceid", defaultValue = "0", required = false) long deviceid) throws EntityNotFound {

        List<Incidences> incidencesList = new ArrayList<>(); // Inicializa la lista vacía

        if (userid != 0) {
            logger.info("/incidences getAll() userId");
            incidencesList = incidenceService.findAllByUserId(userid);
        } else if (deviceid != 0) {
            logger.info("/incidences getAll() deviceId");
            incidencesList = incidenceService.findAllBydevice(deviceid);
        } else {
            logger.info("/incidences getAll()");
            incidencesList = incidenceService.findAll();
        }

        return ResponseEntity.ok(incidencesList);
    }

    @Operation(
            summary = "Retrieve a incidence by his Id number",
            description = "Retrieve a incidence by his Id number.",
            tags = { "incidence"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)
    })
    @GetMapping("/incidences/{idIncidence}")
    public ResponseEntity<Incidences> getOne(@Parameter(description = "ID of the incidence") @PathVariable("idIncidence") long idIncidence) throws EntityNotFound {
        logger.info("/incidences/{idIncidence}");
        Incidences searchIncidence = incidenceService.findById(idIncidence);
        return ResponseEntity.ok(searchIncidence);
    }
    @Operation(
            summary = "Retrieve a incidence by id User",
            description = "Retrieve a incidence by id User.",
            tags = { "incidence","user"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/incidences/user/{idUser}")
    public ResponseEntity<List<Incidences>> getAllIncidencesIdUser(@Parameter(description = "ID of the user") @PathVariable("idUser") long idUser) throws EntityNotFound {
        logger.info("/incidences/user/{idUser} getAllIncidencesIdUser"+idUser);
        List<Incidences> userIncidences = incidenceService.findByIdUser(idUser);
        return ResponseEntity.ok(userIncidences);

    }
    @Operation(
            summary = "Retrieve a incidence by id device",
            description = "Retrieve a incidence by id device.",
            tags = { "incidence","device"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/incidences/device/{deviceId}")
    public ResponseEntity<List<Incidences>> getAllIncidencesIdDevice(@Parameter(description = "ID of device") @PathVariable("deviceId") long deviceId) throws EntityNotFound {
        logger.info("/incidences/device/{deviceId} getAllIncidencesIdDevice"+deviceId);
        List<Incidences> deviceIncidences = incidenceService.findByDevice(deviceId);
        return ResponseEntity.ok(deviceIncidences);

    }
    @Operation(
            summary = "Save a incidence by a user",
            description = "Save a incidence by a user.",
            tags = { "incidence","user"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad request", content = @Content)
    })
    @PostMapping("/incidence/{idUser}")
    public ResponseEntity<Incidences> addOne(@PathVariable("idUser") long idUser, @RequestBody Incidences incidence) throws EntityNotFound {
        try {
            // Obtener el usuario correspondiente
            logger.info("/incidences/{idUser} addOne()");
            User userIncidence = userService.findById(idUser);

            // Obtener los datos del dispositivo desde la incidencia
            Device device = incidence.getDevice();

            // Si el dispositivo no es nulo y tiene un ID, intentar buscarlo en la base de datos
            if (device != null && device.getDeviceId() > 0) {
                device = deviceService.findById(device.getDeviceId());
            } else {
                // El dispositivo no se proporcionó o no tiene un ID válido
                // No se agrega un dispositivo en este punto, ya que podría no ser necesari
            }

            // Asociar la incidencia con el usuario y el dispositivo (si existe)
            incidence.setUser(userIncidence);
            incidence.setDevice(device);

            // Guardar la incidencia en la base de datos
            incidenceService.addIncidence(incidence);

            // Devolver una respuesta exitosa
            return ResponseEntity.status(HttpStatus.CREATED).body(incidence);
        } catch (EntityNotFound ex) {
            // Manejo de excepciones en caso de que el usuario no se encuentre
            logger.error("/incidence/{idUser} addOne() USER NOT FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

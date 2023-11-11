package com.juansenen.gaticket.controllers;

import com.juansenen.gaticket.domain.Incidences;
import com.juansenen.gaticket.domain.Messages;
import com.juansenen.gaticket.domain.Net;
import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.DeviceService;
import com.juansenen.gaticket.service.IncidenceService;
import com.juansenen.gaticket.service.MessageService;
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

import java.util.List;

@RestController
@Tag(name = "message", description = "This controller contains all the endpoints that can manage messages between admin and users")
public class MessageController {

    private final static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;
    @Autowired
    private IncidenceService incidenceService;
    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    @Operation(
            summary = "Retrieve all messages",
            description = "Retrieve all messages.",
            tags = { "messages"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/messages")
    public ResponseEntity<List<Messages>> getAllMenssages(){
        List<Messages> messagesList = messageService.getAll();
        return ResponseEntity.ok(messagesList);
    }
    @Operation(
            summary = "Retrieve all messages by Id of incidence",
            description = "Retrieve all messages by Id of incidence.",
            tags = { "messages"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Incidences.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
    })
    @GetMapping("/messages/{idIncidence}")
    public ResponseEntity<List<Messages>> getAllMenssagesIncidence(@Parameter(description = "ID of the incidence")@PathVariable("idIncidence") long idIncidence) throws EntityNotFound {
        List<Messages> messagesList = messageService.getAllById(idIncidence);
        return ResponseEntity.ok(messagesList);
    }
    @Operation(
            summary = "Save a new message on a Incidence",
            description = "Save a new message on a Incidence.",
            tags = { "messages"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Net.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request",
                    content = @Content),
    })
    @PostMapping("/messages/{idIncidence}/{idEmisor}")
    public ResponseEntity<Messages> saveMessage(@Parameter(description = "ID of the incidence")@PathVariable("idIncidence") long idIncidence,
                                                @Parameter(description = "ID of who send message")@PathVariable("idEmisor") long idEmisor,
                                                @RequestBody Messages message) throws EntityNotFound {
        Incidences incidenceSearch = incidenceService.findById(idIncidence);
        User userSearch = userService.findById(idEmisor);
        message.setIncidenciaMessage(incidenceSearch);
        message.setEmisorMessage(userSearch);

        Messages newMessages = messageService.saveOne(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMessages);
    }



}

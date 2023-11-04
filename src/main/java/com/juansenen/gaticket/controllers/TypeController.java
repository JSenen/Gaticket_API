package com.juansenen.gaticket.controllers;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.DeviceService;
import com.juansenen.gaticket.service.TypeService;
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
@Tag(name = "type", description = "This controller contains all the endpoints that can manage types of devices")
public class TypeController {

    private final static Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;
    @Autowired
    private DeviceService deviceService;

    @Operation(
            summary = "Retrieve all types of device",
            description = "Get all types of device on the data base.",
            tags = { "type"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Type.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/types")
    public ResponseEntity<List<Type>> getAll(
            @Parameter(description = "Search name including letters. Can make real-time request", required = false)
            @RequestParam(name = "query", defaultValue = "", required = false) String typeName) {

        List<Type> searchResults;

        if (!typeName.isEmpty()) {
            logger.info("/types getAll() find by letter");
            searchResults = typeService.findByLetters(typeName);
        } else {
            logger.info("/types getAll()");
            searchResults = typeService.findAll();
        }

        return ResponseEntity.ok(searchResults);
    }
    @Operation(
            summary = "Save a device",
            description = "Save a device on Data Base",
            tags = { "type"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Type.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request",
                    content = @Content),
    })
    @PostMapping("/types")
    public ResponseEntity<Type> addOne(@RequestBody Type newType){
        logger.info("/types adOne()");
        typeService.addOne(newType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newType);
    }
    @Operation(
            summary = "Update a type of possible devices",
            description = "Update the name of a type.",
            tags = { "type"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Type.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request",
                    content = @Content),
    })
    @PutMapping("/type/{idType}")
    public ResponseEntity<Type> modOne(@Parameter(description = "Id of type") @PathVariable("idType") long id, @RequestBody Type type) throws EntityNotFound {
        logger.info("/types modOne");
        Type modType = typeService.updateOne(id,type);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modType);
    }
    @Operation(
            summary = "Set a type to a device",
            description = "Set a type to a device.",
            tags = { "type","device"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Type.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request",
                    content = @Content),
    })
    @PostMapping("/device/{idDevice}/{idType}")
    public ResponseEntity<Device> addTypeDevice(@Parameter(description = "Id of device") @PathVariable("idDevice") long idDevice, @Parameter(description = "Id of type")@PathVariable("idType") long idType) {
        logger.info("/device/{idDevice}/{idType} addTypeDevice");
        Device updateDevice = typeService.updateDeviceType(idDevice, idType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateDevice);
    }
    @Operation(
            summary = "Delete a type by Id",
            description = "Delete a type by Id",
            tags = { "type"})
    @DeleteMapping("/type/{idType}")
    public ResponseEntity<Void> deleteType(@Parameter(description = "Id of type")@PathVariable("idType") long idType) throws EntityNotFound{
        logger.info("/type/{idType}");
        typeService.eraseType(idType);

        return ResponseEntity.noContent().build();
    }

}

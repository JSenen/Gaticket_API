package com.juansenen.gaticket.controllers;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.DeviceService;
import com.juansenen.gaticket.service.NetService;
import com.juansenen.gaticket.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "device", description = "This controller contains all the endpoints that can manage users with the device")

public class DeviceController {

    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);

    @Autowired
    private DeviceService deviceService;
    @Autowired
    private NetService netService;
    @Autowired
    private TypeService typeService;

    @Operation(
            summary = "Retrieve all devices",
            description = "Get all devices on the data base.",
            tags = { "device"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Device.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/device")
    public ResponseEntity<List<Device>> getAll( @Parameter(description = "Serial number of device", required = false)@RequestParam(name = "deviceSerial", defaultValue = "", required = false) String serialNumber,
                                                @Parameter(description = "Ip  of device", required = false)@RequestParam(name = "deviceIp", defaultValue = "", required = false) String ipDevice,
                                                @Parameter(description = "MAC of Device", required = false)@RequestParam(name = "mac", defaultValue = "", required = false) String mac,
                                                @Parameter(description = "Type of Device", required = false)@RequestParam(name = "typeId", defaultValue = "0", required = false) long typeId)
    {
        logger.info("/device getAll()");

        if (!serialNumber.isEmpty()) {
            // Buscar por número de serie
            List<Device> devices = deviceService.searchBySerialNumber(serialNumber);
            logger.info("/device getAll() search by serial number = "+serialNumber);
            return ResponseEntity.ok(devices);
        } else if (!ipDevice.isEmpty()) {
            // Buscar por dirección IP
            long netId = netService.findByNetIp(ipDevice);
            List<Device> devices = deviceService.findByIp(netId);
            logger.info("/device getAll() search by IP="+netId);
            return ResponseEntity.ok(devices);
        } else if (!mac.isEmpty()) {
            // Buscar por mac
            List<Device> devices = deviceService.findByMac(mac);
            logger.info("/device getAll() search by MAC = " + mac);
            return ResponseEntity.ok(devices);
        }else if (typeId > 0){
            //Buscar por tipo
            List<Device> devices = deviceService.findByType(typeId);
            logger.info("/device getAll() search bytype = " + typeId);
            return ResponseEntity.ok(devices);
        }else{
            // Si no se proporciona ningún parámetro de solicitud, devuelve todos los dispositivos.
            logger.info("/device getAll() no parameters provided, returning all devices");
            List<Device> devices = deviceService.findAll();
            return ResponseEntity.ok(devices);
        }
    }
    @Operation(
            summary = "Retrieve a device by his id number",
            description = "Retrieve a device by his id number.",
            tags = { "device"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Device.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/device/{idDevice}")
    public ResponseEntity<Device> getDeviceById(@Parameter(description = "Id device") @PathVariable("idDevice") long idDevice) throws EntityNotFound {
        Device getDevice = deviceService.getOne(idDevice);
        return ResponseEntity.ok(getDevice);
    }
    @Operation(
            summary = "Save a new device",
            description = "Save a new Device.",
            tags = { "device"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Device.class)) }),
            @ApiResponse(responseCode = "400", description = "La dirección MAC debe tener exactamente 12 caracteres",
                    content = @Content),
            @ApiResponse(responseCode = "404",description = "Bad Request",
                    content = @Content )
    })
    @PostMapping("/device")
    public ResponseEntity<Device> addOne(@Valid @RequestBody Device device){
        logger.info("/device addOne()");
        Device newDevice = deviceService.addOne(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDevice);
    }
    @Operation(
            summary = "Assing a new device to a department",
            description = "Assing a device to a department.",
            tags = { "device"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Device.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404",description = "Bad Request",
                    content = @Content )
    })
    @PostMapping("/department/{idDevice}/{idDepartment}")
    public ResponseEntity<Device> addDeviceToDepartment (@Parameter(description = "Id device")@PathVariable("idDevice") long idDevice,
                                                         @Parameter(description = "Id department")@PathVariable("idDepartment") long idDepartment){
        Device deviceDepartment = deviceService.addDeviceDepartment(idDevice, idDepartment);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deviceDepartment);
    }

    @Operation(
            summary = "Delete a device by Id",
            description = "Delete a device by Id",
            tags = { "device"})
    @DeleteMapping("/device/{idDevice}")
    public ResponseEntity<Void> deleteDevice(@Parameter(description = "Id of device")@PathVariable("idDevice") long idDevice) throws EntityNotFound{
        logger.info("/device/{idDevice} deleteDevice()");
        deviceService.eraseDevice(idDevice);

        return ResponseEntity.noContent().build();
    }

    // Manejo de excepciones de validación 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getDefaultMessage()).append("\n");
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }
}

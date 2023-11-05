package com.juansenen.gaticket.controllers;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Type;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                                                @Parameter(description = "Ip  of device", required = false)@RequestParam(name = "ideviceIp", defaultValue = "", required = false) String ipDevice,
                                                @Parameter(description = "Type name", required = false)@RequestParam(name = "typeName", defaultValue = "", required = false) String typeName)
    {
        logger.info("/device getAll()");

        if (!serialNumber.isEmpty()) {
            logger.info("/device getAll() search by serial number");
            // Buscar por número de serie
            List<Device> devices = deviceService.searchBySerialNumber(serialNumber);
            return ResponseEntity.ok(devices);
        } else if (!ipDevice.isEmpty()) {
            logger.info("/device getAll() search by IP");
            // Buscar por dirección IP
            long netId = netService.findByNetIp(ipDevice);
            List<Device> devices = deviceService.findByIp(netId);
            return ResponseEntity.ok(devices);
        } else if (!typeName.isEmpty()){
            logger.info("/device getAll() search by type Name");
            // Buscar por nombre del tipo
            long typeOfDevice = typeService.findByTypeName(typeName);
            List<Device> devices = deviceService.findByType(typeOfDevice);
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
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404",description = "Bad Request",
                    content = @Content )
    })
    @PostMapping("/device")
    public ResponseEntity<Device> addOne(@RequestBody Device device){
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
            tags = { "type"})
    @DeleteMapping("/device/{idDevice}")
    public ResponseEntity<Void> deleteDevice(@Parameter(description = "Id of device")@PathVariable("idDevice") long idDevice) throws EntityNotFound{
        logger.info("/device/{idDevice} deleteDevice()");
        deviceService.eraseDevice(idDevice);

        return ResponseEntity.noContent().build();
    }
}

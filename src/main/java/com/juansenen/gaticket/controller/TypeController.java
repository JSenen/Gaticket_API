package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.DeviceService;
import com.juansenen.gaticket.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
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
            summary = "Retrieve all types",
            description = "Get all types of devices on the data base.",
            tags = { "type"})
    @GetMapping("/types")
    public ResponseEntity<List<Type>> getAll(){
        logger.info("/types getAll()");
        return ResponseEntity.ok(typeService.findAll());
    }
    @Operation(
            summary = "Add a new type",
            description = "Add a new type of device on the data base",
            tags = { "type"})
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
    @PutMapping("/type/{idType}")
    public ResponseEntity<Type> modOne(@PathVariable("idType") long id, @RequestBody Type type) throws EntityNotFound {
        logger.info("/types modOne");
        Type modType = typeService.updateOne(id,type);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modType);
    }
    @Operation(
            summary = "Set a type to a device",
            description = "Set a type to a device.",
            tags = { "type","device"})
    @PostMapping("/device/{idDevice}/{idType}")
    public ResponseEntity<Device> addTypeDevice(@PathVariable("idDevice") long idDevice, @PathVariable("idType") long idType) {
        logger.info("/device/{idDevice}/{idType} addTypeDevice");
        Device updateDevice = typeService.updateDeviceType(idDevice, idType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updateDevice);
    }

}

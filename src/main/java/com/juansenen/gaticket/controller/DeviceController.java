package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.service.DeviceService;
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

    @GetMapping("/device")
    public ResponseEntity<List<Device>> getAll(){
        logger.info("/device getAll()");
        List<Device> deviceList = deviceService.findAll();
        return ResponseEntity.ok(deviceList);
    }

    @PostMapping("/device")
    public ResponseEntity<Device> addOne(@RequestBody Device device){
        logger.info("/device addOne()");
        Device newDevice = deviceService.addOne(device);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDevice);
    }

}

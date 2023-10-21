package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Net;
import com.juansenen.gaticket.repository.DeviceRepository;
import com.juansenen.gaticket.service.DeviceService;
import com.juansenen.gaticket.service.NetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "net", description = "This controller contains all the endpoints that can manage net")
public class NetController {

    private final static Logger logger = LoggerFactory.getLogger(NetController.class);
    @Autowired
    private NetService netService;
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/net")
    public ResponseEntity<List<Net>> getAll(){
        logger.info("/net getAll()");
        List<Net> netList = netService.findAll();
        return ResponseEntity.ok(netList);
    }

    @PostMapping("/net")
    public ResponseEntity<Net> addOne(@RequestBody Net net){
        logger.info("/net addOne()");
        Net newNet = netService.addOne(net);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(net);
    }

    @PostMapping("/net/{idNet}/{idDevice}")
    public ResponseEntity<Device> addDeviceNet(@PathVariable("idNet") long idNet, @PathVariable("idDevice") long idDevice){
        Device addDeviceNet = deviceService.addNetToDevice(idNet, idDevice);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(addDeviceNet);
    }
}

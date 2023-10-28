package com.juansenen.gaticket.controllers;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Net;
import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.service.DeviceService;
import com.juansenen.gaticket.service.NetService;
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
@Tag(name = "net", description = "This controller contains all the endpoints that can manage net")
public class NetController {

    private final static Logger logger = LoggerFactory.getLogger(NetController.class);
    @Autowired
    private NetService netService;
    @Autowired
    private DeviceService deviceService;
    @Operation(
            summary = "Retrieve all network data",
            description = "Get all data about network.",
            tags = { "type"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Net.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/net")
    public ResponseEntity<List<Net>> getAll(){
        logger.info("/net getAll()");
        List<Net> netList = netService.findAll();
        return ResponseEntity.ok(netList);
    }
    @Operation(
            summary = "Save a new net data",
            description = "Save anew net data.",
            tags = { "type"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Net.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request",
                    content = @Content),
    })
    @PostMapping("/net")
    public ResponseEntity<Net> addOne(@RequestBody Net net){
        logger.info("/net addOne()");
        Net newNet = netService.addOne(net);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(net);
    }
    @Operation(
            summary = "Assing a new net data to a device",
            description = "Assing net data to a device.",
            tags = { "type"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Net.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad Request",
                    content = @Content),
    })
    @PostMapping("/net/{idNet}/{idDevice}")
    public ResponseEntity<Device> addDeviceNet(@Parameter(description = "Id of net") @PathVariable("idNet") long idNet,
                                               @Parameter(description = "Id of a device")@PathVariable("idDevice") long idDevice){
        Device addDeviceNet = deviceService.addNetToDevice(idNet, idDevice);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(addDeviceNet);
    }
}

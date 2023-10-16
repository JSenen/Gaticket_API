package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "type", description = "This controller contains all the endpoints that can manage types of devices")
public class TypeController {

    private final static Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;

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
}

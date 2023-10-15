package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.service.TypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeController {

    private final static Logger logger = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public ResponseEntity<List<Type>> getAll(){
        logger.info("/types getAll()");
        return ResponseEntity.ok(typeService.findAll());
    }

    @PostMapping("/types")
    public ResponseEntity<Type> addOne(@RequestBody Type newType){
        logger.info("/types adOne()");
        typeService.addOne(newType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newType);
    }
}

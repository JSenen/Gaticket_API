package com.juansenen.gaticket.controller;


import com.juansenen.gaticket.domain.Rol;
import com.juansenen.gaticket.service.RolService;
import com.juansenen.gaticket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RolController {

    private final static Logger logger = LoggerFactory.getLogger(RolController.class);

    @Autowired
    RolService rolService;
    @Autowired
    UserService userService;

    @PostMapping("/rol")
    public ResponseEntity<Rol> addRoll(@RequestBody Rol rol){
        Rol newRollType = rolService.addRol(rol);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newRollType);
    }

}

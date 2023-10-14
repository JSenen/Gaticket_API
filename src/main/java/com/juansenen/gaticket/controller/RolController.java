package com.juansenen.gaticket.controller;


import com.juansenen.gaticket.domain.Rol;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.RolService;
import com.juansenen.gaticket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/rol/{iduser}")
    public ResponseEntity<Rol> addRollToUser(@PathVariable("iduser") long iduser, @RequestBody Rol rolType) throws EntityNotFound {
        //TODO añadir buscar user por Id para exception
        Rol newRol = rolService.changeRol(iduser, rolType);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newRol);
    }

}

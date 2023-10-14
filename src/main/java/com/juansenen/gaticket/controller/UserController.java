package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** El controlador responsable de recibir las solicitudes HTTP del cliente, procesarlas y enviar respuestas al cliente.
 * @see UserController */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll(){

        return ResponseEntity.ok(userService.findAll());
    }



}

package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.ErrorMessage;
import com.juansenen.gaticket.exception.UserNotFound;
import com.juansenen.gaticket.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** El controlador responsable de recibir las solicitudes HTTP del cliente, procesarlas y enviar respuestas al cliente.
 * @see UserController */
@RestController
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll(){
        logger.info("UserController getAll()");
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User newUser = userService.addOne(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findOne(@PathVariable("id") long id){
        User findUser = userService.findById(id);
        return new ResponseEntity<>(findUser,HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> modUser (@PathVariable("id") long id, @RequestBody User user){
        User modUser = userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delUser (@PathVariable("id") long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();

    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorMessage> lineNoFoundException(UserNotFound unfe){
        logger.error(unfe.getMessage(), unfe);
        ErrorMessage errorMessage = new ErrorMessage(404, unfe.getMessage());
        logger.error("Finish NotFoundException");
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }



}

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
        logger.info("UserController addUser()");
        User newUser = userService.addOne(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findOne(@PathVariable("id") long id) throws UserNotFound{
        logger.info("UserController findOne()");

        User findUser = userService.findById(id);

        return new ResponseEntity<>(findUser,HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> modUser (@PathVariable("id") long id, @RequestBody User user) throws UserNotFound{
        logger.info("UserController modUser()");
        User modUser = userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delUser (@PathVariable("id") long id){
        logger.info("UserController delUser()");
        try {
            userService.deleteUser(id);
        } catch (UserNotFound userNotFound) {
            throw new RuntimeException(userNotFound);
        }
        return ResponseEntity.noContent().build();

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException (Exception exc){
        logger.error(exc.getMessage(), exc);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        logger.error("Finish 500 Internal Server error");
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.Department;
import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.DepartmentService;
import com.juansenen.gaticket.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "user", description = "This controller contains all the endpoints that can manage author user information")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    @Operation(
            summary = "Retrieve all users",
            description = "Get all Users on the data base.",
            tags = { "user"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll(){
        logger.info("UserController getAll()");
        return ResponseEntity.ok(userService.findAll());
    }
    @Operation(
            summary = "Add a new user",
            description = "Add a new user on the data base. By default \"usuario\" is the rol assigned",
            tags = { "user"})
    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) throws EntityNotFound {
        logger.info("UserController addUser()");
        User newUser = userService.addOne(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @Operation(
            summary = "Retrieve a user by his ID number",
            description = "Get a user on the data base by his ID number.",
            tags = { "user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findOne(@Parameter(description = "ID of User search")@PathVariable("id") long id) throws EntityNotFound {
        logger.info("UserController findOne()");

        User findUser = userService.findById(id);

        return new ResponseEntity<>(findUser,HttpStatus.OK);
    }
    @Operation(
            summary = "Update a user information",
            description = "Update the information of a user",
            tags = { "user"})
    @PutMapping("/user/{id}")
    public ResponseEntity<User> modUser (@Parameter(description = "ID of User search") @PathVariable("id") long id, @RequestBody User user) throws EntityNotFound {
        logger.info("UserController modUser()");
        User modUser = userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modUser);
    }
    @Operation(
            summary = "Add a user on a Department",
            description = "Add a user search by his ID on a Department by his Id",
            tags = { "user", "department"})
    @PostMapping("/user/{iduser}/{departmentId}")
    public ResponseEntity<Department> addDepToUSer (@Parameter(description = "ID of User search") @PathVariable("iduser") long id, @Parameter(description = "ID of Department search")@PathVariable("departmentId") long departmentId) throws EntityNotFound {
        Department departmetUser = userService.addDepart(id,departmentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(departmetUser);
    }
    @Operation(
            summary = "Delete a user",
            description = "Add a user search by his ID on a Department by his Id",
            tags = { "user"})
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> delUser (@PathVariable("id") long id){
        logger.info("UserController delUser()");
        try {
            userService.deleteUser(id);
        } catch (EntityNotFound entityNotFound) {
            throw new RuntimeException(entityNotFound);
        }
        return ResponseEntity.noContent().build();

    }
    @Operation(
            summary = "Update rol of a user",
            description = "Update rol of a user search by his Id",
            tags = { "user"})
    @PatchMapping("/user/{iduser}")
    public ResponseEntity<User> updateUserRol(@Parameter(description = "ID of User search") @PathVariable("iduser") long id, @RequestBody User user) throws EntityNotFound{
        User updateUser = userService.updateRolUser(id, user);
        return ResponseEntity.status((HttpStatus.ACCEPTED)).body(updateUser);
    }

   /* @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException (Exception exc){
        logger.error(exc.getMessage(), exc);
        ErrorMessage errorMessage = new ErrorMessage(500, "Internal Server Error");
        logger.error("Finish 500 Internal Server error");
        return new ResponseEntity<>(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }*/

}

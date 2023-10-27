package com.juansenen.gaticket.controllers;

import com.juansenen.gaticket.domain.Department;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.DepartmentService;
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

/** El controlador responsable de recibir las solicitudes HTTP del cliente, procesarlas y enviar respuestas al cliente.
 * @see RestController */
@RestController
@Tag(name = "department", description = "This controller contains all the endpoints that can manage users with the departments")
public class DepartmentController {

    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    DepartmentService departmentService;

    @Operation(
            summary = "Retrieve all departments",
            description = "Get all departments on the data base.",
            tags = { "department"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Department.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
    })
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> gelAll(){
        logger.info("Inicio getAll() departments");
        return ResponseEntity.ok(departmentService.findAll());
    }
    @Operation(
            summary = "Retrieve a department by Id",
            description = "Get one department knowing his Id.",
            tags = { "department"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Department.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
    })
    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> findDepById(@Parameter(description = "ID of department search") @PathVariable("id") long id) throws EntityNotFound {
        Department findDep = departmentService.findById(id);
        return new ResponseEntity<>(findDep,HttpStatus.OK);
    }
    @Operation(
            summary = "Retrieve a department by Id user",
            description = "Get one department knowing id of a user.",
            tags = { "department","user"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Department.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
    })
    @GetMapping("/department/{iduser}")
    public ResponseEntity<Department> findDepartUser(@Parameter(description = "ID of user search") @PathVariable("iduser") long id) throws EntityNotFound {
        Department findDep = departmentService.findByUser(id);
        return new ResponseEntity<>(findDep,HttpStatus.OK);
    }

    @Operation(
            summary = "Save a new department",
            description = "Save one department ",
            tags = { "department"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Department.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content)
    })
    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment (@RequestBody Department department){
        logger.info("Inicio addDepartment()");
        Department newDepartment = departmentService.addOne(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
    }
    @Operation(
            summary = "Update a department by Id",
            description = "Update one department knowing his Id.",
            tags = { "department"})
    @ApiResponses({
            @ApiResponse(responseCode = "202", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Department.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
    })
    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> modDepartment(@Parameter(description = "Department Id") @PathVariable("id") long id, @RequestBody Department department) throws EntityNotFound{
        logger.info("inicio modDepartment()");
        Department modDepar = departmentService.updateDepartment(id, department);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(department);
    }
    @Operation(
            summary = "Delete a department by Id",
            description = "Delete one department knowing his Id.",
            tags = { "department"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content),
    })
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> delDepartment(@Parameter(description = "Id of the department to erase") @PathVariable("id") long id) throws EntityNotFound{
        logger.info("Inicio delDepartment()");
        try {
            departmentService.deleteDepartment(id);
        } catch (EntityNotFound entityNotFound) {
            throw new RuntimeException(entityNotFound);
        }
        return ResponseEntity.noContent().build();

    }

}

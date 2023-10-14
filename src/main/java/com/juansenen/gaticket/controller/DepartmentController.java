package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.Department;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.DepartmentService;
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
public class DepartmentController {

    private final static Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> gelAll(){
        logger.info("Inicio getAll() departments");
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> findDepById(@PathVariable("id") long id) throws EntityNotFound {
        Department findDep = departmentService.findById(id);
        return new ResponseEntity<>(findDep,HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> addDepartment (@RequestBody Department department){
        logger.info("Inicio addDepartment()");
        Department newDepartment = departmentService.addOne(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> modDepartment(@PathVariable("id") long id, @RequestBody Department department) throws EntityNotFound{
        logger.info("inicio modDepartment()");
        Department modDepar = departmentService.updateDepartment(id, department);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(department);
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> delDepartment(@PathVariable("id") long id) throws EntityNotFound{
        logger.info("Inicio delDepartment()");
        try {
            departmentService.deleteDepartment(id);
        } catch (EntityNotFound entityNotFound) {
            throw new RuntimeException(entityNotFound);
        }
        return ResponseEntity.noContent().build();

    }

}

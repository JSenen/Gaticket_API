package com.juansenen.gaticket.controller;

import com.juansenen.gaticket.domain.Incidences;
import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.service.IncidenceService;
import com.juansenen.gaticket.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "incidence", description = "This controller contains all the endpoints that can manage incidences")
public class IncidenceController {

    private final static Logger logger = LoggerFactory.getLogger(Incidences.class);

    @Autowired
    private IncidenceService incidenceService;
    @Autowired
    private UserService userService;

    @GetMapping("/incidences")
    public ResponseEntity<List<Incidences>> getALl(){
        logger.info("/incidences getAll()");
        List<Incidences> incidencesList = incidenceService.findAll();
        return ResponseEntity.ok(incidencesList);
    }

    @GetMapping("/incidences/{idIncidence}")
    public ResponseEntity<Incidences> getOne(@PathVariable("idIncidence") long idIncidence) throws EntityNotFound {
        logger.info("/incidences/{idIncidence}");
        Incidences searchIncidence = incidenceService.findById(idIncidence);
        return ResponseEntity.ok(searchIncidence);
    }

    @PostMapping("/incidence/{idUser}")
    public ResponseEntity<Incidences> addOne(@PathVariable("idUser") long idUser, @RequestBody Incidences incidence) throws EntityNotFound {
        try {
            //Obtener el usuario correspondiente
            logger.info("/incidences/{idUser} addOne()");
            User userIncidence = userService.findById(idUser);

            //Asociar la incidencia con el usuario
            incidence.setUser(userIncidence);

            // Guardamos la incidencia en la base de datos
            incidenceService.addIncidence(incidence);

            //Devolver una respuesta exitosa
            return ResponseEntity.status(HttpStatus.CREATED).body(incidence);
        } catch (EntityNotFound ex) {
            // Manejo de excepciones en caso de que el usuario no se encuentre
            logger.error("/incidence/{idUser} addOne() USER NO FOUND");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}

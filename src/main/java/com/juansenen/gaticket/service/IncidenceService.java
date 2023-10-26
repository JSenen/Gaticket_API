package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Incidences;
import com.juansenen.gaticket.exception.EntityNotFound;

import java.util.List;

public interface IncidenceService {
    List<Incidences> findAll();

    Incidences addIncidence(Incidences incidence);

    Incidences findById(long idIncidence) throws EntityNotFound;

    List<Incidences> findByIdUser(long idUser) throws EntityNotFound;
}

package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Incidences;

import java.util.List;

public interface IncidenceService {
    List<Incidences> findAll();

    Incidences addIncidence(Incidences incidence);
}

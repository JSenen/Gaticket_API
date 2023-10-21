package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Incidences;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.IncidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenceServiceImpl implements IncidenceService{

    @Autowired
    private IncidenceRepository incidenceRepository;

    @Override
    public List<Incidences> findAll() {
        List<Incidences> incidencesList = incidenceRepository.findAll();
        return  incidencesList;
    }

    @Override
    public Incidences addIncidence(Incidences incidence) {
        Incidences saveIncidence = incidenceRepository.save(incidence);
        return saveIncidence;
    }

    @Override
    public Incidences findById(long idIncidence) throws EntityNotFound {
        Incidences incidences = incidenceRepository.findById(idIncidence).orElseThrow(()->new EntityNotFound("Incidence no found"));
        return incidences;
    }
}

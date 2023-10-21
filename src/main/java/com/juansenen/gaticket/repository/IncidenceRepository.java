package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Incidences;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenceRepository extends CrudRepository<Incidences, Long> {

    List<Incidences> findAll();

}

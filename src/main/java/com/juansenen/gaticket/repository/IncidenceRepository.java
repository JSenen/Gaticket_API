package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Incidences;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidenceRepository extends CrudRepository<Incidences, Long> {

    List<Incidences> findAll();

    @Query(value = "SELECT * FROM incidences WHERE user = ?",nativeQuery = true)
    List<Incidences> findAllIncidencesUser(long idUser);
    @Query(value = "SELECT * FROM incidences WHERE device = ?",nativeQuery = true)
    List<Incidences> findAllIncidencesDevice(long deviceId);
}

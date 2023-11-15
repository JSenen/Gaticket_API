package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Messages;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Messages,Long> {

    List<Messages> findAll();

    @Query(value = "SELECT * FROM messages WHERE incidencia_id =:idIncidencia",nativeQuery = true)
    List<Messages> findByIdOfIncidence(@Param("idIncidencia")long idIncidence);


}

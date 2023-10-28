package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Net;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetRepository extends CrudRepository<Net, Long> {

    List<Net> findAll();
}

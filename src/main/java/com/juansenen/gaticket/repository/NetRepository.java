package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Net;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetRepository extends CrudRepository<Net, Long> {

    List<Net> findAll();

    @Query(value = "SELECT * FROM net WHERE net_ip = ?",nativeQuery = true)
    Net findByNetString(String ipDevice);
}

package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepository extends CrudRepository<Type, Long> {

    List<Type> findAll();

    @Query(value = "SELECT * FROM types WHERE type_type LIKE ?1", nativeQuery = true)
    List<Type> findTypeByteToByte(String typeName);
}

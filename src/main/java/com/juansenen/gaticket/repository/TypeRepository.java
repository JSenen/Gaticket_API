package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Type;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypeRepository extends CrudRepository<Type, Long> {

    List<Type> findAll();
}

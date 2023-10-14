package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Capa de acceso a datos que se encarga de interactuar con la base de datos
 * Proporciona métodos para realizar operaciones de lectura y escritura
 * Abstrae la logica de acceso a los datos
 * @see UserRepository*/
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

}

package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** Capa de acceso a datos que se encarga de interactuar con la base de datos
 * Proporciona métodos para realizar operaciones de lectura y escritura
 * Abstrae la logica de acceso a los datos
 * @see UserRepository*/
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();
    Optional<User> findById(long id);

    @Query(value = "SELECT * FROM users WHERE user_tip = ?",nativeQuery = true)
    List<User> findByUserTip(String serialNumber);

}

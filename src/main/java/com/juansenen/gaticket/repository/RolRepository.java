package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Rol;
import org.springframework.data.repository.CrudRepository;

public interface RolRepository extends CrudRepository<Rol, Long> {
    Rol findByRolType(String rolType);


}

package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Rol;
import com.juansenen.gaticket.exception.EntityNotFound;

public interface RolService {

    Rol addRol(Rol rol);

    Rol changeRol(long iduser, Rol rolType) throws EntityNotFound;

}

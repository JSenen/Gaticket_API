package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Rol;
import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.RolRepository;
import com.juansenen.gaticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService{

    @Autowired
    RolRepository rolRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Rol addRol(Rol rol) {
        Rol newRolType = rolRepository.save(rol);
        return newRolType;
    }

    @Override
    public Rol changeRol(long iduser, Rol rolType) throws EntityNotFound {
        User userChangeRol = userRepository.findById(iduser).orElseThrow(()->new EntityNotFound("User not found"));
        // Verifica que el rol exista en la base de datos
        Rol exitingRol = rolRepository.findByRolType(rolType.getRolType());
        userChangeRol.setUserRol(exitingRol);
        //Guardamos usuario modificado el rol
        userRepository.save(userChangeRol);
        return exitingRol;
    }
}

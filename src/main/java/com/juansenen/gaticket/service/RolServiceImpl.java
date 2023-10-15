package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Rol;
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


}

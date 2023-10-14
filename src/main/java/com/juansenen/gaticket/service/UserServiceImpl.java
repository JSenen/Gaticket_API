package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/** Realiza operaciones concretas, como la validación de datos, la llamada al repositorio
 * para acceder a la base de datos y la manipulación de datos.
 *  @see UserServiceImpl
 *  @see User */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return null;
    }
}

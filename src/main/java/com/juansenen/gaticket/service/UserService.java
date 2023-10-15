package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;

import java.util.List;

/** Interface conjunto de métodos que representan las operaciones de alto nivel que se pueden realizar en la aplicación
 * @see UserService
 * @see User */
public interface UserService {

    List<User> findAll();

    User addOne(User user) throws EntityNotFound;

    User findById(long id) throws EntityNotFound;

    User updateUser(long id, User user) throws EntityNotFound;

    void deleteUser(long id) throws EntityNotFound;
}

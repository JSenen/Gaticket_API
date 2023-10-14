package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.User;

import java.util.List;
/** Interface conjunto de métodos que representan las operaciones de alto nivel que se pueden realizar en la aplicación
 * @see UserService
 * @see User */
public interface UserService {

    List<User> findAll();

    User addOne(User user);

    User findById(long id);

    User updateUser(long id, User user);
}

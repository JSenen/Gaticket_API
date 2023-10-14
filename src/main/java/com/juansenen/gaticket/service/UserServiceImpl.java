package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.UserNotFound;
import com.juansenen.gaticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User addOne(User user){
        User newUser = userRepository.save(user);
        return newUser;
    }

    @Override
    public User findById(long id) throws UserNotFound {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFound("Usuario no encontrado"); // Lanza la excepción cuando el usuario no se encuentra
        }
    }

    @Override
    public User updateUser(long id, User user) throws UserNotFound {
        User modUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFound("El usuario no se encuentra"));
        modUser.setUserMail(user.getUserMail());
        modUser.setUserPassword(user.getUserPassword());
        modUser.setUserTip(user.getUserTip());
        return userRepository.save(modUser);
    }

    @Override
    public void deleteUser(long id) throws UserNotFound {
        userRepository.deleteById(id);
    }
}

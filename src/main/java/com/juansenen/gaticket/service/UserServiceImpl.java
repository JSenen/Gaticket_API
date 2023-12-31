package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Department;
import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.DepartmentRepository;
import com.juansenen.gaticket.repository.IncidenceRepository;
import com.juansenen.gaticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private IncidenceRepository incidenceRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<User> findAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public User addOne(User user) {
        //La contraseña del usuario se encriptara en la base de datos
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = user.getUserPassword(); //Contraseña texto plano
        String hashedPassword = passwordEncoder.encode(plainPassword); // Contraseña Hash
        user.setUserPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public User findById(long id) throws EntityNotFound {
        Optional<User> userOptional = userRepository.findById(id);

        /*if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new EntityNotFound("Usuario no encontrado"); // Lanza la excepción cuando el usuario no se encuentra
        }*/
        return userOptional.get();
    }

    @Override
    public User updateUser(long id, User user) throws EntityNotFound {
        User modUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("El usuario no se encuentra"));
        modUser.setUserMail(user.getUserMail());
        modUser.setUserPassword(user.getUserPassword());
        modUser.setUserTip(user.getUserTip());
        return userRepository.save(modUser);
    }

    @Override
    public void deleteUser(long id) throws EntityNotFound {
        User user = userRepository.findById(id).orElseThrow(()->new EntityNotFound("User not found"));
        userRepository.deleteById(id);
    }

    @Override
    public User updateRolUser(long id, User user) throws EntityNotFound {
        User modUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFound("El usuario no se encuentra"));
        modUser.setUserRol(user.getUserRol());
        userRepository.save(modUser);
        return modUser;
    }

    @Override
    public Department addDepart(long id, long idDepartment) {
        Optional<User> userOptional = userRepository.findById(id);
        Department departmentOptional = departmentRepository.findById(idDepartment);
        //El usuario y el departamento existen

           //TODO indicar no encontrado
        User user = userOptional.get();
        Department department = departmentOptional;
        //Guardamos en el usuario el departamento
        user.setUserDepartment(department);
        userRepository.save(user);
        return department;
    }

    @Override
    public List<User> searchByTipNumber(String userTip) {
        List<User> usersList = userRepository.findByUserTip(userTip);
        return  usersList;
    }
}

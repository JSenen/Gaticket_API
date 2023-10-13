package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return null;
    }
}

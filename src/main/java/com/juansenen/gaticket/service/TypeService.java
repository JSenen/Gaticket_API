package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAll();

    Type addOne(Type newType);
}

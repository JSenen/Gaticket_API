package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.exception.EntityNotFound;

import java.util.List;

public interface TypeService {
    List<Type> findAll();

    Type addOne(Type newType);

    Type updateOne(long id, Type type) throws EntityNotFound;

    Device updateDeviceType(long idDevice, long idType);

    List<Type> findByLetters(String typeName);

    void eraseType(long idType);

    long findByMac(String typeName);

    Type findByIdType(long idType);
}

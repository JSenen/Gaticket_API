package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;
    @Override
    public List<Type> findAll() {
        List<Type> typeList = typeRepository.findAll();
        return typeList;
    }

    @Override
    public Type addOne(Type newType) {
        Type newTypeDevice = typeRepository.save(newType);
        return newTypeDevice;
    }
}

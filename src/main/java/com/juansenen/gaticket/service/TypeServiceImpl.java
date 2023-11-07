package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.DeviceRepository;
import com.juansenen.gaticket.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService{

    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private DeviceRepository deviceRepository;
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

    @Override
    public Type updateOne(long id, Type type) throws EntityNotFound {
        Type updateType =typeRepository.findById(id).orElseThrow(()->new EntityNotFound("Type not found"));
        updateType.setTypeName(type.getTypeName());

        return typeRepository.save(updateType);
    }

    @Override
    public Device updateDeviceType(long idDevice, long idType) {
        Optional<Device> changeDevice = deviceRepository.findById(idDevice);
        Optional<Type> asignedType = typeRepository.findById(idType);

        Device updatedDevice = changeDevice.get();
        Type searchType = asignedType.get();

        updatedDevice.setDeviceType(searchType);
        deviceRepository.save(updatedDevice);

        return updatedDevice;
    }

    @Override
    public List<Type> findByLetters(String typeName) {
        List<Type> searchResults = typeRepository.findTypeByteToByte('%' + typeName + '%');

        if (searchResults == null || searchResults.isEmpty()) {
            return Collections.emptyList(); // Devuelve una lista vacía si no hay resultados
        }

        return searchResults;
    }

    @Override
    public void eraseType(long idType) {
        Optional<Type> typeSearch = typeRepository.findById(idType);
        //Buscamos los dispositivos con ese tipo para poner el tipo a nulo.
        // Obtén los dispositivos relacionados a este tipo y establece la referencia en nulo
        List<Device> dispositivos = deviceRepository.findByDeviceTypeId(idType);
        for (Device dispositivo : dispositivos) {
            dispositivo.setDeviceType(null);
            deviceRepository.save(dispositivo);
        }
        typeRepository.deleteById(idType);
    }

    @Override
    public long findByMac(String typeName) {
        long typesearch = typeRepository.findByName(typeName);
        return typesearch;
    }

    @Override
    public Type findByIdType(long idType) {
        Optional<Type> searchType = typeRepository.findById(idType);
        return searchType.get();
    }
}

package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.DeviceRepository;
import com.juansenen.gaticket.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        //Comprobar Device y Type existen
        Optional<Device> deviceOptional = deviceRepository.findById(idDevice);
        Optional<Type> typeOptional = typeRepository.findById(idType);

        //recuperamos el dispositivo
        Device device = deviceOptional.get();
        Type newType = typeOptional.get();
        //guardamos dispositivo con el id de tipo
        device.setDeviceTypeId(newType);
        deviceRepository.save(device);
        return device;

    }
}

package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Incidences;
import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.DeviceRepository;
import com.juansenen.gaticket.repository.IncidenceRepository;
import com.juansenen.gaticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidenceServiceImpl implements IncidenceService{

    @Autowired
    private IncidenceRepository incidenceRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Incidences> findAll() {
        List<Incidences> incidencesList = incidenceRepository.findAll();
        return  incidencesList;
    }

    @Override
    public Incidences addIncidence(Incidences incidence) {
        Incidences saveIncidence = incidenceRepository.save(incidence);
        return saveIncidence;
    }

    @Override
    public Incidences findById(long idIncidence) throws EntityNotFound {
        Incidences incidences = incidenceRepository.findById(idIncidence).orElseThrow(()->new EntityNotFound("Incidence no found"));
        return incidences;
    }

    @Override
    public List<Incidences> findByIdUser(long idUser) throws EntityNotFound {
        User usersearch = userRepository.findById(idUser).orElseThrow(()->new EntityNotFound("User no found"));
        List<Incidences> incidencesList = incidenceRepository.findAllIncidencesUser(idUser);
        return incidencesList;
    }

    @Override
    public List<Incidences> findByDevice(long deviceId) throws EntityNotFound {
        Device devicesearch = deviceRepository.findById(deviceId).orElseThrow(()->new EntityNotFound("Device no found"));
        List<Incidences> incidencesList = incidenceRepository.findAllIncidencesDevice(deviceId);
        return incidencesList;
    }

}

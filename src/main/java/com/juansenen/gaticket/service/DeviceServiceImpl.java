package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.repository.DeviceRepository;
import com.juansenen.gaticket.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public List<Device> findAll() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList;
    }

    @Override
    public Device addOne(Device device) {
        Device newDevice = deviceRepository.save(device);
        return newDevice;
    }

}

package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
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

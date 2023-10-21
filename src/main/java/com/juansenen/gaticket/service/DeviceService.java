package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;

import java.util.List;

public interface DeviceService {
    List<Device> findAll();

    Device addOne(Device device);



}

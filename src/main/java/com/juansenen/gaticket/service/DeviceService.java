package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.exception.EntityNotFound;

import java.util.List;

public interface DeviceService {
    List<Device> findAll();

    Device addOne(Device device);


    Device addDeviceDepartment(long idDevice, long idDepartment);

    Device addNetToDevice(long idNet, long idDevice);

    Device getOne(long idDevice) throws EntityNotFound;

    List<Device> searchBySerialNumber(String serialNumber);

    List<Device> searchByType(long idType);

    List<Device> findByIp(long ipDevice);

    void eraseDevice(long idDevice);
}

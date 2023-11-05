package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Department;
import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Net;
import com.juansenen.gaticket.domain.Type;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.DepartmentRepository;
import com.juansenen.gaticket.repository.DeviceRepository;
import com.juansenen.gaticket.repository.NetRepository;
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
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private NetRepository netRepository;
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

    @Override
    public Device addDeviceDepartment(long idDevice, long idDepartment) {

        Optional<Device> deviceOptional = deviceRepository.findById(idDevice);
        Department departmentOptional = departmentRepository.findById(idDepartment);

        Device updateDevice = deviceOptional.get();

        //Agregamos dispositivo al listado del departamento
        departmentOptional.getDevices().add(updateDevice);
        departmentRepository.save(departmentOptional);
        //Actualizamos el listado
        updateDevice.setDepartment(departmentOptional);

        return deviceRepository.save(updateDevice);
    }

    @Override
    public Device addNetToDevice(long idNet, long idDevice) {
        Optional<Device> changeDevice = deviceRepository.findById(idDevice);
        Optional<Net> asignedNet = netRepository.findById(idNet);

        Device updatedDevice = changeDevice.get();
        Net searchNet = asignedNet.get();

        updatedDevice.setNet(searchNet);
        searchNet.setNetStatus(true);
        deviceRepository.save(updatedDevice);
        netRepository.save(searchNet);

        return updatedDevice;
    }

    @Override
    public Device getOne(long idDevice) throws EntityNotFound {
        Device searchDevice = deviceRepository.findById(idDevice).orElseThrow(()-> new EntityNotFound("Entity no found"));
        return searchDevice;
    }

    @Override
    public List<Device> searchBySerialNumber(String serialNumber) {
        List<Device> devices = deviceRepository.findBySerialNumber(serialNumber);
        return  devices;
    }

    @Override
    public List<Device> searchByType(long idType) {
        List<Device> devices = deviceRepository.findByDeviceTypeId(idType);
        return devices;
    }

    @Override
    public List<Device> findByIp(long ipDevice) {
        List<Device> devices = deviceRepository.findByIp(ipDevice);
        return  devices;
    }

    @Override
    public void eraseDevice(long idDevice) {
        Optional<Device> deviceSearch = deviceRepository.findById(idDevice);

        deviceRepository.deleteById(idDevice);
    }

}

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
        // Formatear la dirección MAC antes de guardarla
        String formattedMac = formatearDireccionMac(device.getDeviceMac());
        device.setDeviceMac(formattedMac);

        Device newDevice = deviceRepository.save(device);
        return newDevice;
    }

    // Método para formatear la dirección MAC con dos puntos
    private String formatearDireccionMac(String direccionMac) {
        if (direccionMac != null) {
            direccionMac = direccionMac.replaceAll("(.{2})", "$1:").substring(0, 17);
        }
        return direccionMac;
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
        // Buscar dispositivo
        Optional<Device> deviceSearch = deviceRepository.findById(idDevice);

        if (deviceSearch.isPresent()) {
            Device deviceToDelete = deviceSearch.get();

            // Verificar si el dispositivo tiene una relación con una Net
            Net net = deviceToDelete.getNet();

            if (net != null) {
                // Si el dispositivo tiene una relación con una Net, liberar la IP
                long netId = net.getNetId();
                Optional<Net> netToFree = netRepository.findById(netId);

                if (netToFree.isPresent()) {
                    Net freeNet = netToFree.get();
                    freeNet.setNetStatus(false);
                    netRepository.save(freeNet);
                }
            }

            // Eliminar el dispositivo
            deviceRepository.deleteById(idDevice);
        }
    }

    @Override
    public List<Device> findByType(long typeName) {
        List<Device> devices = deviceRepository.searchByType(typeName);
        return devices;
    }

}

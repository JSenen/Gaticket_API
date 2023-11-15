package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Department;
import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Net;
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
            // Comprobar si la dirección MAC ya tiene ":". Si no los tiene, lod agregamos
            if (!direccionMac.contains(":")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < direccionMac.length(); i += 2) {
                    sb.append(direccionMac.substring(i, i + 2));
                    if (i + 2 < direccionMac.length()) {
                        sb.append(":");
                    }
                }
                direccionMac = sb.toString();
            }
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
    public List<Device> findByMac(String mac) {
        List<Device> devices = deviceRepository.searchByMac(mac);
        return devices;
    }

    @Override
    public void addDevice(Device device) {
        deviceRepository.save(device);
    }

    @Override
    public Device findById(long deviceId) {
        Optional<Device> device = deviceRepository.findById(deviceId);
        return device.get();
    }

    @Override
    public List<Device> findByType(long typeId) {
        List<Device> devices = deviceRepository.findByType(typeId);
        return devices;
    }

}

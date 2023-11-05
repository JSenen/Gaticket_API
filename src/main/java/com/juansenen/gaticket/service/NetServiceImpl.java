package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Net;
import com.juansenen.gaticket.domain.User;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.DeviceRepository;
import com.juansenen.gaticket.repository.NetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class NetServiceImpl implements NetService{

    @Autowired
    private NetRepository netRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Net> findAll() {
        List<Net> netList = netRepository.findAll();
        return netList;
    }

    @Override
    public Net addOne(Net net) {
        Net newNet = netRepository.save(net);
        return newNet;
    }

    @Override
    public long findByNetIp(String ipDevice) {
        long netId = netRepository.findByNetString(ipDevice).getNetId();

        return netId;
    }

    @Override
    public void eraseIp(long idNet) {
        Optional<Net> netSearch = netRepository.findById(idNet);
        //Buscamos los dispositivos con esa ip asignada
        // Obtén los dispositivos relacionados a esa IP y ponemos en nulo
        List<Device> dispositivos = deviceRepository.findByIp(idNet);
        for (Device dispositivo : dispositivos) {
            dispositivo.setNet(null);
            deviceRepository.save(dispositivo);
        }
        netRepository.deleteById(idNet);
    }

    @Override
    public String findDepartmentByIp(long idNet) {
        Optional<Net> net = netRepository.findById(idNet);
        String departmentName = netRepository.findDepartByNetId(idNet).toString();
        return departmentName;
    }

    @Override
    public Net updateStatusIp(long idNet, Net netBody) throws EntityNotFound {
        //Buscamos la IP
        Net updateIp = netRepository.findById(idNet)
                .orElseThrow(() -> new EntityNotFound("IP not found"));
        //Cambiamos estado de la IP
        updateIp.setNetStatus(netBody.isNetStatus());
        //Buscamos dispositivo con la IP
        long deviceId = netRepository.findDeviceWithIp(idNet);
        Optional<Device> deviceToChange = deviceRepository.findById(deviceId);
        Device device = deviceToChange.get();
        //Establecer IP del dispositivo como nulo
        device.setNet(null);
        deviceRepository.save(device);
        netRepository.save(updateIp);
        return updateIp;
    }
}

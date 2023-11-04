package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Net;
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
}

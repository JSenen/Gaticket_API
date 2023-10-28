package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Net;
import com.juansenen.gaticket.repository.NetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class NetServiceImpl implements NetService{

    @Autowired
    private NetRepository netRepository;


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
}

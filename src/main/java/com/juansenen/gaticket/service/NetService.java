package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Net;
import com.juansenen.gaticket.exception.EntityNotFound;

import java.util.List;

public interface NetService {
    List<Net> findAll();

    Net addOne(Net net);

    long findByNetIp(String ipDevice);

    void eraseIp(long idNet);

    String findDepartmentByIp(long idNet);

    Net updateStatusIp(long idNet, Net netBody) throws EntityNotFound;
}

package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Net;

import java.util.List;

public interface NetService {
    List<Net> findAll();

    Net addOne(Net net);
}

package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    List<Device> findAll();
}

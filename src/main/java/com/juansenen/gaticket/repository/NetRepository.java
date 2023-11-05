package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Device;
import com.juansenen.gaticket.domain.Net;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NetRepository extends CrudRepository<Net, Long> {

    List<Net> findAll();

    @Query(value = "SELECT * FROM net WHERE net_ip = ?",nativeQuery = true)
    Net findByNetString(String ipDevice);

    @Query(value = "SELECT d.department_name FROM department d JOIN device dev ON d.department_id = dev.department JOIN net n ON dev.net_id = n.net_id WHERE n.net_id = :idNet", nativeQuery = true)
    List<String> findDepartByNetId(@Param("idNet") long idNet);

    @Query(value = "SELECT device_id FROM device WHERE net_id =:idNet",nativeQuery = true)
    long findDeviceWithIp(@Param("idNet") long idNet);
}

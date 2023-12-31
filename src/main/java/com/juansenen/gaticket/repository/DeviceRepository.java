package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends CrudRepository<Device, Long> {

    List<Device> findAll();

    @Override
    Optional<Device> findById(Long idDevice);

    @Query(value = "SELECT * FROM device WHERE device_serialnumber = ?",nativeQuery = true)
    List<Device> findBySerialNumber(String serialNumber);
    @Query(value = "SELECT * FROM device WHERE type_id = ?",nativeQuery = true)
    List<Device> findByDeviceTypeId(long idType);

    @Query(value = "SELECT * FROM device WHERE net_id = ?",nativeQuery = true)
    List<Device> findByIp(long ipDevice);


    void deleteById(long idDevice);
    @Query(value = "SELECT * FROM device WHERE device_mac = ?",nativeQuery = true)
    List<Device> searchByMac(String typeName);

    @Query(value = "SELECT * FROM device WHERE type_id =:typeId",nativeQuery = true)
    List<Device> findByType(@Param("typeId") long typeId);
}

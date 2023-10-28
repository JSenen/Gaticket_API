package com.juansenen.gaticket.repository;

import com.juansenen.gaticket.domain.Department;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    List<Department> findAll();
    Department findById(long id);

}

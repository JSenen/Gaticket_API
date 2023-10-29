package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Department;
import com.juansenen.gaticket.exception.EntityNotFound;

import java.util.List;

public interface DepartmentService {

    List<Department> findAll();

    Department addOne(Department department);

    Department findById(long id) throws EntityNotFound;

    Department updateDepartment(long id, Department department) throws EntityNotFound;

    void deleteDepartment(long id) throws EntityNotFound;

    Department findByUser(long id) throws EntityNotFound;
}

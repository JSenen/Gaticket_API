package com.juansenen.gaticket.service;

import com.juansenen.gaticket.domain.Department;
import com.juansenen.gaticket.exception.EntityNotFound;
import com.juansenen.gaticket.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @Override
    public Department addOne(Department department) {
        Department newDepartment = departmentRepository.save(department);
        return department;
    }

    @Override
    public Department findById(long id) throws EntityNotFound {
        Department findDepart = departmentRepository.findById(id);
        if (findDepart != null) {
            return findDepart;
        } else {
            throw new EntityNotFound("Departamento no encontrado");
        }
    }

    @Override
    public Department updateDepartment(long id, Department department) throws EntityNotFound {
        Department updateDepart = departmentRepository.findById(id);
        updateDepart.setDepartmentName(department.getDepartmentName());
        updateDepart.setDepartmentMail(department.getDepartmentMail());
        updateDepart.setDepartmentPhone(department.getDepartmentPhone());
        updateDepart.setDepartmentCity(department.getDepartmentCity());
        return updateDepart;
    }

    @Override
    public void deleteDepartment(long id) throws EntityNotFound{
        Department eraseDepart = departmentRepository.findById(id);
        departmentRepository.deleteById(id);

    }
}

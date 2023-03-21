package com.healthcaredental.reception.employeeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeTypeService {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;
    public List<EmployeeType> getAllEmployeeTypes() {

        List<EmployeeType> employeeTypes = new ArrayList<>();

        employeeTypeRepository.findAll().
                forEach(employeeTypes::add);

        return employeeTypes;



    }

    public EmployeeType getEmployeeType(int id) {

        return employeeTypeRepository.findById(id).get();
    }

    public void addEmployeeType(EmployeeType employeeType) {

        employeeTypeRepository.save(employeeType);
    }

    public void updateEmployeeType(EmployeeType employeeType) {

        employeeTypeRepository.save(employeeType);
    }

    public void deleteEmployeeType(int id) {

        employeeTypeRepository.deleteById(id);
    }
}

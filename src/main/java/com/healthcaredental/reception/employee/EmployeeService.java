package com.healthcaredental.reception.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(String cabinetId) {

        List<Employee> employees = new ArrayList<>();

        employeeRepository.findByCabinetId(cabinetId)
                .forEach(employees::add);
        return employees;

    }

    public Employee getEmployee(String id) {

        return employeeRepository.findById(id).get();
    }

    public void addEmployee(Employee employee) {

        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {

        employeeRepository.save(employee);
    }

    public void deleteEmployee(String id) {

        employeeRepository.deleteById(id);
    }
}

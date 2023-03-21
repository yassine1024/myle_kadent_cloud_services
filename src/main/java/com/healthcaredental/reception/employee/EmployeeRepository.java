package com.healthcaredental.reception.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    public List<Employee> findByCabinetId(String id);
}

package com.healthcaredental.reception.employee;

import com.healthcaredental.reception.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    public List<Employee> findByCabinetId(String id);

    @Query("SELECT p FROM Patient p JOIN p.employees e WHERE e.id = :employeeId")
    List<Patient> findPatientsByEmployeeId(@Param("employeeId") String employeeId);
}

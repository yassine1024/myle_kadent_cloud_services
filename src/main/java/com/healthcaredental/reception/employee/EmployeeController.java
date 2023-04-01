package com.healthcaredental.reception.employee;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.Patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cabinet/{cabinetId}/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PatientService patientService;


    @GetMapping("")
    public List<Employee> getAllEmployees(@PathVariable String cabinetId){

        return employeeService.getAllEmployees(cabinetId);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id){

        return employeeService.getEmployee(id);
    }

    @PostMapping("")
    public void addEmployee(@RequestBody Employee employee){

        employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employee employee){

        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public  void deleteEmployee(@PathVariable String id){

        employeeService.deleteEmployee(id);
    }


}

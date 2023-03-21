package com.healthcaredental.reception.employeeType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee-type")
public class EmployeeTypeController {

    @Autowired
    private EmployeeTypeService employeeTypeService;

    @GetMapping("")
    public List<EmployeeType> getAllEmployeeTypes(){

        return employeeTypeService.getAllEmployeeTypes();
    }

    @GetMapping("/{id}")
    public EmployeeType getEmployeeType(@PathVariable int id){

        return employeeTypeService.getEmployeeType(id);
    }

    @PostMapping("")
    public void addEmployeeType(@RequestBody EmployeeType employeeType){

        employeeTypeService.addEmployeeType(employeeType);
    }

    @PutMapping("/{id}")
    public  void  updateEmployeeType(@RequestBody EmployeeType employeeType){

        employeeTypeService.updateEmployeeType(employeeType);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeType(@PathVariable int id){

        employeeTypeService.deleteEmployeeType(id);
    }
}

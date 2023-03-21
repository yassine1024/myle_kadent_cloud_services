package com.healthcaredental.reception.employeeType;

import com.github.javafaker.Faker;
import com.healthcaredental.reception.patient.TestConfig;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class EmployeeTypeServiceTest {

    @Autowired
    private EmployeeTypeService employeeTypeService;

    //getAllEmployeeTypes
    @Test
    void test2() {

        employeeTypeService.getAllEmployeeTypes()
                .forEach(employeeType -> {
                    System.out.println("Employee Id: "+employeeType.getId()+" Title: "+employeeType.getEmployeeType());
                });

    }

    @Test
    void getEmployeeType() {
    }

    //addEmployeeType
    @Test
    void test1() {

        for (int i = 0; i < 20; i++) {

            EmployeeType employeeType = new EmployeeType();
            Faker faker = new Faker();

            employeeType.setEmployeeType(faker.job().title());

            employeeTypeService.addEmployeeType(employeeType);
        }
    }

    @Test
    void updateEmployeeType() {
    }

    @Test
    void deleteEmployeeType() {
    }
}
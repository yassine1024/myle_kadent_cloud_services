package com.healthcaredental.reception.employee;

import com.github.javafaker.Faker;
import com.healthcaredental.reception.cabinet.CabinetService;
import com.healthcaredental.reception.employee.assistant.Assistant;
import com.healthcaredental.reception.employee.medecin.Medecin;
import com.healthcaredental.reception.employeeType.EmployeeTypeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
class EmployeeServiceTest {


    @Autowired
    EmployeeService employeeService;
    @Autowired
    CabinetService cabinetService;
    @Autowired
    EmployeeTypeService employeeTypeService;

    @Test
    void getAllEmployees() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void addEmployee() {

        for (int i = 0; i < 2; i++) {

            Faker faker = new Faker();
            Employee employee = new Assistant();

            employee.setId(faker.idNumber().valid());
            employee.setFirstName(faker.name().firstName());
            employee.setLastName(faker.name().lastName());
            employee.setAddress(faker.address().streetAddress());
            employee.setCabinet(cabinetService.getCabinet("029-36-0230"));

            employee.setPhoto(faker.file().fileName());

            employeeService.addEmployee(employee);
        }

    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}
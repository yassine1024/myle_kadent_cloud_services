package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.EmployeeService;
import com.healthcaredental.reception.employee.medecin.Medecin;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
class RendezvousServiceTest {

    @Autowired
    private RendezvousService rendezvousService;
    @Autowired
    private EmployeeService employeeService;

    @Test
    void addRendezvous() {

        Rendezvous rendezvous = new Rendezvous();
        rendezvous.setPatient(new Patient("-1219625691"));
        rendezvous.setMedecin(new Medecin("524-44-5151"));
        rendezvous.setDate("2023-04-18");
        rendezvous.setTime("9:55");
        String message= rendezvousService.addRendezvous(rendezvous);
        System.out.println(message);

    }

    @Test
    void addRendezvousBulk(){
        for (int i = 0; i < 1; i++) {
           addRendezvous();
        }
    }


}
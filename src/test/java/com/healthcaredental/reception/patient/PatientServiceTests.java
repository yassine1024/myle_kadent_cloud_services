package com.healthcaredental.reception.patient;


import com.github.javafaker.Faker;
import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.Patient.PatientService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
public class PatientServiceTests {

    @Autowired
    private PatientService patientService;

    @Test
    public void testAddPatient() {
        for (int i = 0; i < 25; i++) {

            Faker faker = new Faker();

            Patient patient = new Patient();
            patient.setId("");
            patient.setFirstName(faker.name().firstName());
            patient.setLastName(faker.name().lastName());
            patient.setPhoneNumber(faker.phoneNumber().phoneNumber());
            patient.setAddress(faker.address().streetAddress());
            patientService.addPatient(patient);


        }


    }

    @Test
    public void getPatientsByDoctor() {

        String id = "517-61-5102";
        List<Patient> patients = patientService.getPatientsByDoctor(id);

        patients.forEach(patient -> {
            System.out.println("Patient : " + patient.getFirstName());
        });
    }


}

package com.healthcaredental.reception.patient;


import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.Patient.PatientService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
@Import(TestConfig.class)
public class PatientServiceTests {

    @Autowired
    private PatientService patientService;

    @Test
    public void testAddPatient() {
        for (int i = 0; i < 11; i++) {


            Patient patient = new Patient();
            patient.setId("" + i);
            patient.setFirstName("Atik" + i);
            patient.setLastName("Yassine" + i);
            patient.setPhoneNumber("05456232" + i);
            patient.setAddress("Blida");
            patientService.addPatient(patient);


        }

        patientService.getAllPatients().forEach(patient1 -> {
            System.out.println("Patient: " + patient1.getFirstName() + " " + patient1.getLastName() + " ID: " + patient1.getId());
        });


        Assertions.assertEquals("Atik2", patientService.getPatient("2066464510").getFirstName());

    }


}

package com.healthcaredental.reception.functionality;

import com.healthcaredental.reception.Patient.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HashPatientToString {

    @Autowired
    private HashObjectToString<Patient>  hashPatientToString;


    @Test
    public void testHashPatientToString(){

        Patient p = new Patient("","Atik","Yassine",
                "0696273757","Blida");

        try {
            System.out.println(hashPatientToString.hasheObjectToString(p));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

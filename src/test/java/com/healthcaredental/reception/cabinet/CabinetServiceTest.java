package com.healthcaredental.reception.cabinet;

import com.github.javafaker.Faker;
import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.medecin.Medecin;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CabinetServiceTest {

    @Autowired
    public CabinetService cabinetService;

    @Test
    void test1() {

        for(int i = 0; i <2;i++){

            Faker faker = new Faker();
            Cabinet cabinet = new Cabinet();
            cabinet.setId(faker.idNumber().ssnValid());
            cabinet.setAddress(faker.address().fullAddress());
            cabinet.setPhoneNumber(faker.phoneNumber().phoneNumber());
            cabinet.setName(faker.name().fullName());

            cabinetService.addCabinet(cabinet);
        }
    }
    @Test
    void test2() {



        cabinetService.getAllCabinet().forEach(cabinet -> {
                   System.out.println("Cabinet: "+cabinet.getId()+" name: "+cabinet.getName()
                   +" phone: "+cabinet.getPhoneNumber()+" Address: "+cabinet.getAddress());

               });

    }

    //getCabinet
    @Test
    void test4() {

        Cabinet cabinet= cabinetService.getCabinet("93");
        System.out.println("Cabinet 93 after update: "+cabinet.getName()
        +" Address "+cabinet.getAddress()+" Phone: "+cabinet.getPhoneNumber());
    }


//updateCabinet
    @Test
    void test3() {

        Cabinet cabinet = cabinetService.getCabinet("93");

        cabinet.setAddress("Blida");

        cabinetService.updateCabinet(cabinet);
    }

    //deleteCabinet
    @Test
    void test5() {


        cabinetService.deleteCabinet("105");
        cabinetService.getAllCabinet().forEach(cabinet -> {
            System.out.println("Cabinet: "+cabinet.getId()+" name: "+cabinet.getName()
                    +" phone: "+cabinet.getPhoneNumber()+" Address: "+cabinet.getAddress());

        });
    }


    @Test
    public void getPatientsByCabinet() {

        List<Patient> patients= cabinetService.getPatientsByCabinet("740-04-7106");

        patients.forEach( patient -> {
                    System.out.println("Patient "+patient.getLastName()
                    +" "+patient.getFirstName());
        }
        );
    }

    @Test
    public void getDoctorsByCabinet() {

        List<Medecin> doctors= cabinetService.getDoctorsByCatbinet("576-40-1283kfjfjk");

        doctors.forEach( doctor -> {
                    System.out.println("Doctor:  "+doctor.getLastName()
                            +" "+doctor.getFirstName());
                }
        );
    }
}
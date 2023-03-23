package com.healthcaredental.reception.cabinet;

import com.github.javafaker.Faker;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CabinetServiceTest {

    @Autowired
    public CabinetService cabinetService;

    @Test
    void test1() {

        for(int i = 0; i <10;i++){

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
}
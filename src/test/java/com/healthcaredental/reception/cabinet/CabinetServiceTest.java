package com.healthcaredental.reception.cabinet;

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
class CabinetServiceTest {

    @Autowired
    public CabinetService cabinetService;

    @Test
    void test1() {

        for(int i = 0; i <10;i++){
            System.out.println("i::::"+i);
            Faker faker = new Faker();
            Cabinet cabinet = new Cabinet();
            cabinet.setId(i+"");
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
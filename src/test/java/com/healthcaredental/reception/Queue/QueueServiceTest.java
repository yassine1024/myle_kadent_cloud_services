package com.healthcaredental.reception.Queue;

import com.healthcaredental.reception.employee.Employee;
import com.healthcaredental.reception.employee.EmployeeService;
import com.healthcaredental.reception.employee.medecin.Medecin;
import com.healthcaredental.reception.rendezvous.Rendezvous;
import org.junit.jupiter.api.Test;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.name=application-test")
class QueueServiceTest {

    private static final Logger log = LoggerFactory.getLogger(QueueServiceTest.class);
    @Autowired
    private QueueService queueService;
    @Autowired
    private EmployeeService employeeService;

    @Test
    void initializeQueue() {

        log.info(() -> "===== initializeQueue =====");
        queueService.initializeQueue("576-40-1283");
    }

    @Test
    void getAllQueuesByCabinet() {

        log.info(() -> "====== getAllQueuesByCabinetTest ==========");
        queueService.getAllQueuesByCabinet("740-04-7106")
                .forEach(queue -> {
                    System.out.println("que: " + queue.getRendezvousId() +
                            " Rendezvous time: " + queue.getRendezvous().getTime() +
                            " Patient: " + queue.getRendezvous().getPatient().getFirstName() + " " + queue.getRendezvous().getPatient().getLastName() +
                            " To medecin " + queue.getRendezvous().getMedecin().getFirstName() + " " + queue.getRendezvous().getMedecin().getLastName());
                });
    }

    @Test
    void patientArrive() {


        Queue queue = queueService.getQueueById(8l);
        queueService.patientArrive(queue);
    }


    @Test
    void patientQuitRoom() {

        Queue queue = queueService.getQueueById(8l);
//        queueService.patientQuitRoom(queue);

    }

    @Test
    void patientInsideRoom() {

        Queue queue = queueService.getQueueById(8l);
        queueService.patientInsideRoom(queue, "740-04-7106");
    }
}
package com.healthcaredental.reception.Queue;

import com.healthcaredental.reception.date.DateManagement;
import com.healthcaredental.reception.employee.Employee;
import com.healthcaredental.reception.employee.medecin.Medecin;
import com.healthcaredental.reception.rendezvous.Rendezvous;
import com.healthcaredental.reception.rendezvous.RendezvousRepository;
import com.healthcaredental.reception.rendezvous.RendezvousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;
    private List<Queue> queues = new ArrayList<Queue>();

    //    @PostConstruct
    @Transactional
    public void initializeQueue(String id) {

        String today = DateManagement.getDateOfToday();
        if (!queueRepository.checkCabinetQueueInitialized(id, today)) {

            System.out.println("initialize queue");
            queueRepository.initializeCabinetQueue(id, today);

        } else {
            System.out.println("Queue already exist for cabinet : " + id);
        }

    }

    public List<Queue> getAllQueuesByCabinet(String id) {


        queueRepository.getAllQueuesByCabinet(id)
                .forEach(queues::add);

        return queues;
    }

    public void patientArrive(Queue queue) {
        queue.setArrive(true);
//        queue.setArriveTime(DateManagement.getTimeHhMmFormat());
        queueRepository.save(queue);
    }


    public void patientQuitRoom(Queue queue) {
        queue.setOutside(true);
        queue.setQuitTime(DateManagement.getTimeHhMmFormat());
        queue.setInside(false);
        queueRepository.save(queue);
    }

    public void patientInsideRoom(Queue queue) {
        queue.setInside(true);
        queue.setInsideTime(DateManagement.getTimeHhMmFormat());
        queue.setOutside(false);
        queueRepository.save(queue);
    }

    public Queue getQueueById(Long id) {

        return queueRepository.findByRendezvousId(id);
    }

    public List<Queue> getAllQueues() {

        return queueRepository.findAll();
    }
}

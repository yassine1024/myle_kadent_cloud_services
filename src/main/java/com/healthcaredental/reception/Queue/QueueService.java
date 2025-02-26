package com.healthcaredental.reception.Queue;

import com.healthcaredental.reception.date.DateManagement;
import com.healthcaredental.reception.employee.Employee;
import com.healthcaredental.reception.employee.medecin.Medecin;
import com.healthcaredental.reception.employee.medecin.MedecinRepository;
import com.healthcaredental.reception.rendezvous.Rendezvous;
import com.healthcaredental.reception.rendezvous.RendezvousRepository;
import com.healthcaredental.reception.rendezvous.RendezvousService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueueService {


    private final QueueRepository queueRepository;
    private final RendezvousRepository rendezvousRepository;
    private final MedecinRepository medecinRepository;

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


    public void patientQuitRoom(Long id) {

        Queue queue = queueRepository.findByRendezvousId(id);

        queue.setOutside(true);
        queue.setQuitTime(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        queueRepository.save(queue);
    }

    public void patientInsideRoom(Queue queue, String medecinId) {

        Medecin medecin = medecinRepository.findById(medecinId).get();
        System.err.println("Medecin : " + medecin.getId());
        Rendezvous rendezvous = rendezvousRepository.findById(queue.getRendezvousId()).get();
        rendezvous.setMedecin(medecin);
        rendezvousRepository.save(rendezvous);

        queueRepository.save(queue);
    }

    public Queue getQueueById(Long id) {

        return queueRepository.findByRendezvousId(id);
    }

    public List<Queue> getAllQueues() {

        return queueRepository.findAll();
    }
}

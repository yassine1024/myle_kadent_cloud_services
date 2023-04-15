package com.healthcaredental.reception.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/cabinets/{id}/queues")
public class QueueController {

    @Autowired
    private QueueService queueService;

    //this function executed only once, when the application starts up.
    @PostConstruct
    public void initializeQueueOnlyForFirstTime(@PathVariable String id){

        queueService.initializeQueue(id);
    }

    @GetMapping
    public List<Queue> getAllQueuesByCabinet(@PathVariable String id){

       return queueService.getAllQueuesByCabinet(id);
    }

    @PutMapping("/{rdvId}/Arrive")
    public void patientArrive(@RequestBody Queue queue){

        queueService.patientArrive(queue);
    }

    @PutMapping("/{rdvId}/affect_patient_to_medecin")
    public void affectPatientToMedecin(@RequestBody Queue queue){

        queueService.affectPatientToMedecin(queue);
    }

    @PutMapping("/{rdvId}/patient_quit")
    public void patientQuitRoom(@RequestBody Queue queue){

        queueService.patientQuitRoom(queue);
    }
}

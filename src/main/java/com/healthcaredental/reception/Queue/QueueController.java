package com.healthcaredental.reception.Queue;

import com.healthcaredental.reception.employee.medecin.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/cabinets/{id}/queues")
public class QueueController {

    @Autowired
    private QueueService queueService;

    //this function executed only once, when the application starts up.
//    @PostConstruct
    public void initializeQueueOnlyForFirstTime(@PathVariable String id) {

        queueService.initializeQueue(id);
    }

    /* @GetMapping
     public List<Queue> getAllQueuesByCabinet(@PathVariable String id){

        return queueService.getAllQueuesByCabinet(id);
     }*/
    @GetMapping
    public ResponseEntity<List<Queue>> getAllQueuesByCabinet(@PathVariable String id) {

        return ResponseEntity.ok(queueService.getAllQueues());
    }

    @GetMapping("/{rdvId}")
    public Queue getQueueById(@PathVariable Long id) {

        return queueService.getQueueById(id);
    }

    @PutMapping("/{rdvId}/Arrive")
    public void patientArrive(@RequestBody Queue queue) {

        queueService.patientArrive(queue);
    }

    @PutMapping("/{rdvId}/patient_quit")
    public void patientQuitRoom(@RequestBody Queue queue) {

        queueService.patientQuitRoom(queue);
    }

    @PutMapping("/{rdvId}/patient_inside")
    public void patientInsideRoom(@RequestBody Queue queue) {

        queueService.patientInsideRoom(queue);
    }
}

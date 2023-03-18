package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RendezvousController {


    @Autowired
    private RendezvousService rendezvousService;

    @GetMapping("/patients/{patientId}/rendezvous")
    public List<Rendezvous> getAllRendezvousByPatient(@PathVariable String patientId) {


        return rendezvousService.getAllRendezvousByPatient(patientId);
    }
    @GetMapping("/rendezvous")
    public List<Rendezvous> getAllRendezvous() {

        return rendezvousService.getAllRendezvous();
    }

    @GetMapping("/rendezvous/{id}")
    public Rendezvous getRendezvous(@PathVariable("id") String id) {

        return rendezvousService.getRendezvous(id);
    }

    @PostMapping("/patients/{patientId}/rendezvous")
    public void addRendezvous(@PathVariable String patientId, @RequestBody Rendezvous rendezvous) {


        rendezvous.setPatient(new Patient(patientId));
        rendezvousService.addRendezvous(rendezvous);
    }

    @PutMapping("/patients/{patientId}/rendezvous/{id}")
    public void updateRendezvous(@PathVariable String id, @PathVariable String patientId, @RequestBody Rendezvous rendezvous) {

        rendezvous.setPatient(new Patient(patientId));
        rendezvousService.updateRendezvous(rendezvous);

    }

    @DeleteMapping("/rendezvous/{id}")
    public void deleteRendezvous(@PathVariable String id){
        rendezvousService.deleteRendezvous(id);
    }
}

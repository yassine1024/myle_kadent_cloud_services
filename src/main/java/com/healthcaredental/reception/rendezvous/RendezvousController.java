package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RendezvousController {


    @Autowired
    private RendezvousService rendezvousService;

    @GetMapping("/patients/{patientId}/rendezvous")
    public List<Rendezvous> getAllRendezvousByPatient(@PathVariable String patientId) {


        return rendezvousService.getAllRendezvousByPatient(patientId);
    }
    @GetMapping("/medecin/{medecinId}/rendezvous")
    public List<Rendezvous> getAllRendezvousByDoctor(@PathVariable String medecinId) {


        return rendezvousService.getAllRendezvousByDoctor(medecinId);
    }
    @GetMapping("/rendezvous")
    public List<Rendezvous> getAllRendezvous() {

        return rendezvousService.getAllRendezvous();
    }

    @GetMapping("/rendezvous/{id}")
    public Rendezvous getRendezvous(@PathVariable("id") Long id) {

        return rendezvousService.getRendezvous(id);
    }

    @PostMapping("/patients/{patientId}/rendezvous")
    public Map<String, String> addRendezvous(@PathVariable String patientId, @RequestBody Rendezvous rendezvous) {


        rendezvous.setPatient(new Patient(patientId));
        String text= rendezvousService.addRendezvous(rendezvous);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, JSON!");
        response.put("status", "success");
        response.put("value", text);
        return response;
    }

    @PutMapping("/patients/{patientId}/rendezvous/{id}")
    public void updateRendezvous(@PathVariable String id, @PathVariable String patientId, @RequestBody Rendezvous rendezvous) {

        rendezvous.setPatient(new Patient(patientId));
        rendezvousService.updateRendezvous(rendezvous);

    }

    @DeleteMapping("/rendezvous/{id}")
    public void deleteRendezvous(@PathVariable Long id){
        rendezvousService.deleteRendezvous(id);
    }
}

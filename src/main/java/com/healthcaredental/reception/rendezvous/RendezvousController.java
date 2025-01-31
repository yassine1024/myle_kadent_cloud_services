package com.healthcaredental.reception.rendezvous;

import com.healthcaredental.reception.Patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cabinets/{id}")
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
    public ResponseEntity<List<Rendezvous>> getAllRendezvous(@PathVariable String id) {

        return ResponseEntity.ok(rendezvousService.getAllRendezvous(id));
    }
    @GetMapping("/rendezvous/date/{date}")
    public ResponseEntity<List<Rendezvous>> getAllRendezvousByDate(@PathVariable String date) {

        return ResponseEntity.ok(rendezvousService.getAllRendezvousByDate(date));
    }

    @GetMapping("/rendezvous/{rendezvousId}")
    public Rendezvous getRendezvous(@PathVariable("rendezvousId") Long rendezvousId) {

        return rendezvousService.getRendezvous(rendezvousId);
    }

    @PostMapping("/patients/{patientId}/rendezvous")
    public Map<String, String> addRendezvous(@PathVariable String patientId, @RequestBody Rendezvous rendezvous) {

        Patient patient = new Patient();
        patient.setId(patientId);
        rendezvous.setPatient(patient);
        String text = rendezvousService.addRendezvous(rendezvous);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, JSON!");
        response.put("status", "success");
        response.put("value", text);
        return response;
    }

    @PutMapping("/patients/{patientId}/rendezvous/{rendezvousId}")
    public ResponseEntity<Boolean> updateRendezvous(@PathVariable String rendezvousId, @PathVariable String patientId, @RequestBody Rendezvous rendezvous) {

        Patient patient = new Patient();
        patient.setId(patientId);
        rendezvous.setPatient(patient);
      return  ResponseEntity.ok(rendezvousService.updateRendezvous(rendezvous));

    }

    @DeleteMapping("/rendezvous/{rendezvousId}")
    public void deleteRendezvous(@PathVariable Long rendezvousId) {
        rendezvousService.deleteRendezvous(rendezvousId);
    }
}

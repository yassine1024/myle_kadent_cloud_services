package com.healthcaredental.reception.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("")
    public ResponseEntity<List<Patient>> getAllPatients() {

        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable("id") String id) {

        return patientService.getPatient(id);
    }

    @PostMapping
    public void addPatient(@RequestBody Patient patient) {

        patientService.addPatient(patient);
    }

    @PutMapping("/{id}")
    public void updatePatient(@RequestBody Patient patient) {

        patientService.updatePatient(patient);

    }
    
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable String id){
        patientService.deletePatient(id);
    }

}

package com.healthcaredental.reception.Patient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/cabinets/{id}/patients")
@RequiredArgsConstructor
public class PatientController {


    private final PatientService patientService;

    @GetMapping("")
    public ResponseEntity<List<Patient>> getAllPatientsByCabinet(@PathVariable String id) {

        return ResponseEntity.ok(patientService.getAllPatientsByCabinet(id));
    }

    @GetMapping("/meta-data")
    public ResponseEntity<List<PatientDTO>> getAllPatientsWithMetaData() {

        return ResponseEntity.ok(patientService.getAllPatientsWithMetaData());
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable("id") String id) {

        return patientService.getPatient(id);
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient, @PathVariable String id) {

      return ResponseEntity.ok(patientService.addPatient(patient, id));
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

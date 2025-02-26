package com.healthcaredental.reception.Patient;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PatientDTO>> getAllPatientsWithMetaData(@PathVariable String id) {

        return ResponseEntity.ok(patientService.getAllPatientsWithMetaData(id));
    }

    @GetMapping("/{patientId}")
    public Patient getPatient(@PathVariable("patientId") String patientId) {

        return patientService.getPatient(patientId);
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient, @PathVariable String id) {

      return ResponseEntity.ok(patientService.addPatient(patient, id));
    }

    @PutMapping("/{patientId}")
    public void updatePatient(@RequestBody Patient patient) {

        patientService.updatePatient(patient);

    }
    
    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable String patientId){
        patientService.deletePatient(patientId);
    }

}

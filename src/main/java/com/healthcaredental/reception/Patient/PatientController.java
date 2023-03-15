package com.healthcaredental.reception.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @GetMapping("")
    public List<Patient> getAllPatients(){

            return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable("id") String id){

        return patientService.getPatient(id);
    }



}

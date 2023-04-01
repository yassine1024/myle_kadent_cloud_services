package com.healthcaredental.reception.employee.medecin;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.Patient.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class MedecinController {


    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}/patients")
    public List<Patient> getPatientsByDoctor(@PathVariable String id) {

        return patientService.getPatientsByDoctor(id);
    }


}

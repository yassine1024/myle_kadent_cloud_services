package com.healthcaredental.reception.employee.medecin;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.Patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cabinet/{cabinetId}/doctors")
@RequiredArgsConstructor
public class MedecinController {



    private final PatientService patientService;
    private final MedecinService medecinService;

    @GetMapping("/{id}/patients")
    public List<Patient> getPatientsByDoctor(@PathVariable String id) {

        return patientService.getPatientsByDoctor(id);
    }

    @GetMapping
    public ResponseEntity<List<Medecin>>  getMedecinsByCabinet(@PathVariable String cabinetId) {
        return ResponseEntity.ok(medecinService.getMedecinsByCabinet(cabinetId)) ;
    }

}

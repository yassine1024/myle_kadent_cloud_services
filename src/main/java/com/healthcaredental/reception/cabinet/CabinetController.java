package com.healthcaredental.reception.cabinet;


import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.medecin.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cabinet")
public class CabinetController {

    @Autowired
    private CabinetService cabinetService;

    @GetMapping("")
    public List<Cabinet> getAllCabinet() {


        return cabinetService.getAllCabinet();
    }

    @GetMapping("/{id}")
    public Cabinet getCabinet(@PathVariable("id") String id) {

        return cabinetService.getCabinet(id);
    }

    @PostMapping("")
    public void addCabinet(@RequestBody Cabinet cabinet) {



        cabinetService.addCabinet(cabinet);
    }

    @PutMapping("/{id}")
    public void updateCabinet(@RequestBody Cabinet cabinet) {


        cabinetService.updateCabinet(cabinet);

    }

    @DeleteMapping("/{id}")
    public void deleteCabinet(@PathVariable String id){
        cabinetService.deleteCabinet(id);
    }

    @GetMapping("/{id}/patients")
    public ResponseEntity<List<Patient>> getPatientsByCabinet(@PathVariable("id") String id) {

        List<Patient> patients = cabinetService.getPatientsByCabinet(id);
        return ResponseEntity.ok(patients);

    }

    @GetMapping("/{id}/doctors")
    public ResponseEntity<List<Medecin>> getDoctorsByCabinet(@PathVariable("id") String id) {

        List<Medecin> doctors = cabinetService.getDoctorsByCatbinet(id);
        return ResponseEntity.ok(doctors);

    }
}

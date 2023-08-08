package com.healthcaredental.reception.cabinet;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.medecin.Medecin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CabinetService {

    @Autowired
    private CabinetRepository cabinetRepository;

    public List<Cabinet> getAllCabinet() {

        List<Cabinet> cabinets = new ArrayList<Cabinet>();

        cabinetRepository.findAll().
                forEach(cabinets::add);

        return cabinets;


    }

    public Cabinet getCabinet(String id) {

        return cabinetRepository.findById(id).get();

    }

    public void addCabinet(Cabinet cabinet) {

        cabinetRepository.save(cabinet);
    }

    public void updateCabinet(Cabinet cabinet) {
        cabinetRepository.save(cabinet);
    }

    public void deleteCabinet(String id) {

        cabinetRepository.deleteById(id);
    }

    public List<Patient> getPatientsByCabinet(String id) {
        return cabinetRepository.getPatientsByCabinet(id);
    }

    public List<Medecin> getDoctorsByCatbinet(String id) {
        return cabinetRepository.getDoctorsByCabinet(id);
    }
}

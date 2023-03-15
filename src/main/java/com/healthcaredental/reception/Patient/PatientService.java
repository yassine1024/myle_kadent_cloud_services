package com.healthcaredental.reception.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;


    public List<Patient> getAllPatients(){

        List<Patient> patients= new ArrayList<Patient>();

        patientRepository.findAll()
                .forEach(patients::add);

        return patients;

    }
}

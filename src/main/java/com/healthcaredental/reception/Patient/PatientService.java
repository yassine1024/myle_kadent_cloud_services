package com.healthcaredental.reception.Patient;

import com.healthcaredental.reception.functionality.HashObjectToString;
import com.healthcaredental.reception.functionality.HashPatientToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private HashObjectToString<Patient> hashPatientToString;

    public List<Patient> getAllPatients(){

        List<Patient> patients= new ArrayList<Patient>();

        patientRepository.findAll()
                .forEach(patients::add);

        return patients;

    }

    public Patient getPatient(String id) {

       return patientRepository.findById(id).get();
    }

    public void addPatient(Patient patient) {

        try {
            patient.setId(hashPatientToString.hasheObjectToString(patient));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        patientRepository.save(patient);
    }

    public void updatePatient(Patient patient) {

        patientRepository.save(patient);
    }


    public void deletePatient(String id) {

        patientRepository.deleteById(id);
    }
}

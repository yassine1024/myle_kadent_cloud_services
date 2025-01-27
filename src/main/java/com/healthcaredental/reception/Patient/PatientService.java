package com.healthcaredental.reception.Patient;

import com.healthcaredental.reception.functionality.HashObjectToString;
import com.healthcaredental.reception.functionality.HashPatientToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private HashObjectToString<Patient> hashPatientToString;

    public List<Patient> getAllPatients() {

        List<Patient> patients = new ArrayList<Patient>();

        patientRepository.findAll()
                .forEach(patients::add);

        return patients;

    }

    public Patient getPatient(String id) {

        return patientRepository.findById(id).get();
    }

    public Patient addPatient(Patient patient) {

        try {
            patient.setId(hashPatientToString.hasheObjectToString(patient));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
      return  patientRepository.save(patient);
    }

    public void updatePatient(Patient patient) {

        patientRepository.save(patient);
    }


    public void deletePatient(String id) {

        patientRepository.deleteById(id);
    }

    public List<Patient> getPatientsByDoctor(String id) {


        return patientRepository.findPatientsByDoctorId(id);
    }

    public List<PatientDTO> getAllPatientsWithMetaData() {
        List<Object[]> results = patientRepository.findAllWithMetaData();
        List<PatientDTO> patientDTOs = new ArrayList<>();

        for (Object[] result : results) {
            String patientId = (String) result[0];

            Patient patient = this.getPatient(patientId);
            BigInteger confirmedAppointment = (BigInteger) result[1];
            BigInteger missingConfirmedAppointment = (BigInteger) result[2];
            BigInteger postponedAppointment = (BigInteger) result[3];

            PatientDTO patientDTO = new PatientDTO(patient,
                    confirmedAppointment.intValue(),
                    missingConfirmedAppointment.intValue(),
                    postponedAppointment.intValue());
            patientDTOs.add(patientDTO);
        }

        return patientDTOs;
    }
}

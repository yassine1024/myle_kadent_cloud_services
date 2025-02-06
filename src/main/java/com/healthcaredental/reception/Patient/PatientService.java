package com.healthcaredental.reception.Patient;

import com.healthcaredental.reception.cabinet.Cabinet;
import com.healthcaredental.reception.cabinet.CabinetRepository;
import com.healthcaredental.reception.cabinet.CabinetVisit;
import com.healthcaredental.reception.cabinet.CabinetVisitRepository;
import com.healthcaredental.reception.functionality.HashObjectToString;
import com.healthcaredental.reception.functionality.HashPatientToString;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {


    private final PatientRepository patientRepository;
    private final HashObjectToString<Patient> hashPatientToString;
    private final CabinetVisitRepository cabinetVisitRepository;
    private final CabinetRepository cabinetRepository;

    public List<Patient> getAllPatientsByCabinet(String id) {
        List<CabinetVisit> visits = cabinetVisitRepository.findByCabinetId(id);
        List<Patient> patients = new ArrayList<>();

        for (CabinetVisit visit : visits) {
            patients.add(visit.getPatient());
        }

        return patients;

    }

    public Patient getPatient(String id) {

        return patientRepository.findById(id).get();
    }

    public Patient addPatient(Patient patient, String id) {

        try {
            patient.setId(hashPatientToString.hasheObjectToString(patient));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        Cabinet cabinet = cabinetRepository.findById(id).get();
        CabinetVisit cabinetVisit= new CabinetVisit();
        cabinetVisit.setCabinet(cabinet);

        Patient savedPatient =  patientRepository.save(patient);
        cabinetVisit.setPatient(savedPatient);
        cabinetVisitRepository.save(cabinetVisit);

      return  savedPatient;
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

    public List<PatientDTO> getAllPatientsWithMetaData(String cabinetId) {
        List<Object[]> results = patientRepository.findAllWithMetaData(cabinetId);
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

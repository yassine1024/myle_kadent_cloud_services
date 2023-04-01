package com.healthcaredental.reception.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, String> {
    @Query("SELECT DISTINCT rdv.patient FROM Rendezvous rdv WHERE rdv.medecin.id = :medecinId")
    List<Patient> findPatientsByDoctorId(@Param("medecinId") String medecinId);
}

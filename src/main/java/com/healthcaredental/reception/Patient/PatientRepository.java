package com.healthcaredental.reception.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, String> {
    @Query("SELECT DISTINCT rdv.patient FROM Rendezvous rdv WHERE rdv.medecin.id = :medecinId")
    List<Patient> findPatientsByDoctorId(@Param("medecinId") String medecinId);


    @Query(value = "SELECT p.id, " +
            "COUNT(rd.rendezvous_id) AS confirmed_appointment, " +
            "COUNT(CASE WHEN rd.arrive_time IS NULL THEN rd.rendezvous_id ELSE NULL END) AS missing_confirmed_appointment, " +
            "COUNT(rp.id) AS postponed_appointment " +
            "FROM Patient p " +
            "LEFT JOIN Rendezvous r ON r.patient_id = p.id " +
            "LEFT JOIN Rendezvous_Post_Poned rp ON rp.rendezvous_id = r.id " +
            "LEFT JOIN Rendezvous_Detail rd ON rd.rendezvous_id = r.id " +
            "GROUP BY p.id " +
            "ORDER BY  postponed_appointment DESC , missing_confirmed_appointment DESC ", nativeQuery = true)
    List<Object[]> findAllWithMetaData();

   /* @Query("SELECT NEW com.healthcaredental.reception.Patient.PatientDTO(p, " +
            "COUNT( CASE WHEN rd.arriveTime='10' THEN 1), " +
            "COUNT(CASE WHEN rd.arriveTime IS NULL THEN rd.id ELSE NULL END), " +
            "COUNT(rp)) " +
            "FROM Patient p " +
            "LEFT JOIN p.rendezvous r " +
            "LEFT JOIN r.rendezvousPostPoneds rp " +
            "LEFT JOIN r.rendezvousDetails rd " +
            "GROUP BY p")
    List<PatientDTO> findAllWithMetaData();*/
}

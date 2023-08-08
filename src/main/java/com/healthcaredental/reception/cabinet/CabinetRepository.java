package com.healthcaredental.reception.cabinet;

import com.healthcaredental.reception.Patient.Patient;
import com.healthcaredental.reception.employee.medecin.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CabinetRepository  extends JpaRepository<Cabinet, String> {

    @Query("SELECT DISTINCT p FROM Patient p" +
            " JOIN p.treates  m" +
            " WHERE m.cabinet.id = :id")
    List<Patient> getPatientsByCabinet(String id);

    @Query("SELECT DISTINCT m FROM Medecin m" +
            " JOIN m.cabinet  c" +
            " WHERE m.cabinet.id = :id")
    List<Medecin> getDoctorsByCabinet(String id);
}

package com.healthcaredental.reception.employee.medecin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, String> {

    List<Medecin> findByCabinetId(String cabinetId);

    @Query("SELECT COUNT(m) FROM Medecin m WHERE m.cabinet.id = :cabinetId")
    long countByCabinetId(@Param("cabinetId") String cabinetId);
}

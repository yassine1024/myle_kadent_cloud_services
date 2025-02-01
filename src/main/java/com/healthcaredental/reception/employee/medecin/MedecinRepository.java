package com.healthcaredental.reception.employee.medecin;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, String> {

    List<Medecin> findByCabinetId(String cabinetId);
}
